package com.requiem.main;

import com.requiem.ast.*;
import com.requiem.builder.ASTBuilder;
import com.requiem.parser.RequiemV2Lexer;
import com.requiem.parser.RequiemV2Parser;
import com.requiem.error.RequiemErrorListener;
import com.requiem.error.RequiemLexerErrorListener;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class RequiemAnalyzer {

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE_BOLD = "\u001B[1;37m";

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: Requiem.jar <file.rq>");
            return;
        }
        try {
            Path rqfile = Paths.get(args[0]);
            String input = Files.readString(rqfile);

            CharStream charStream = CharStreams.fromString(input);

            // Setup lexer with custom error listener
            RequiemV2Lexer lexer = new RequiemV2Lexer(charStream);
            lexer.removeErrorListeners();
            lexer.addErrorListener(new RequiemLexerErrorListener());

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Setup parser with custom error listener
            RequiemV2Parser parser = new RequiemV2Parser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg, RecognitionException e) {
                    throw new ParseCancellationException(
                            String.format("Syntax Error - Line %d:%d - %s",
                                    line, charPositionInLine + 1, msg));
                }
            });

            // Parse the input
            RequiemV2Parser.ProgramContext tree = parser.program();
            System.out.println("✓ Parsing completed successfully");

            // Semantic analysis with custom error listener
            ParseTreeWalker walker = new ParseTreeWalker();
            RequiemErrorListener errorListener = new RequiemErrorListener();
            walker.walk(errorListener, tree);

            // Check for semantic errors
            if (errorListener.hasErrors()) {
                errorListener.throwIfErrors();
            } else {
                System.out.println("✓ Semantic analysis passed");
                ParseTreeWalker astWalker = new ParseTreeWalker();
                ASTBuilder astBuilder = new ASTBuilder();
                astWalker.walk(astBuilder, tree);

                ProgramNode program = astBuilder.getProgram();
                printAnalysisReport(program);
            }


        } catch (ParseCancellationException e ) {
            System.err.println("✗ Error detected:");
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("✗ Unexpected error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }


private static void printAnalysisReport(ProgramNode program) {
        System.out.println(WHITE_BOLD + "=" + "=".repeat(78) + RESET);
        System.out.println(WHITE_BOLD + "                    REQUIREMENT PRIORITIZATION ANALYSIS REPORT" + RESET);
        System.out.println(WHITE_BOLD + "=" + "=".repeat(78) + RESET + "\n");

        // Features summary
        System.out.println(CYAN + "FEATURES SUMMARY:" + RESET);
        System.out.printf("%-10s %-40s %-10s %-10s %-10s %-30s%n",
                "ID", "TITLE", "VALUE", "EFFORT", "RISK", "DEPENDENCIES");
        System.out.println("-".repeat(100));

        for (FeatureNode feature : program.getFeatures().values()) {
            String title = feature.getTitle().length() > 40 ?
                    feature.getTitle().substring(0, 37) + "..." : feature.getTitle();
            System.out.printf("%-10s %-40s %-10d %-10d %-10d %-30s%n",
                    feature.getId(),
                    title,
                    feature.getBusinessValue(),
                    feature.getEffort(),
                    feature.getRisk(),
                    feature.getDependencies());
        }

        System.out.println();

        // Release plans analysis
        for (ReleasePlanNode release : program.getReleasePlans()) {
            System.out.println(BLUE + "RELEASE PLAN: " + WHITE_BOLD + release.getId() + RESET);
            System.out.println(BLUE + "Target Date: " + RESET +
                    release.getTargetDate().format(DateTimeFormatter.ISO_DATE));
            System.out.println(BLUE + "Days until deadline: " + RESET + release.getDaysUntilDeadline());
            System.out.println(BLUE + "Team Capacity: " + RESET + release.getTeamCapacity() + " story points");
            System.out.println(BLUE + "Current Progress: " + RESET + release.getCurrentProgress() + "%\n");

            System.out.println(PURPLE + "PRIORITY ALLOCATION:" + RESET);

            for (ReleasePlanNode.MoscowCategory category : ReleasePlanNode.MoscowCategory.values()) {
                var assignments = release.getAllocations().get(category);
                if (!assignments.isEmpty()) {
                    System.out.println("\n" + YELLOW + category.getLabel().toUpperCase() + ":" + RESET);
                    System.out.printf("%-15s %-20s %-20s%n",
                            "Feature ID", "Priority Score", "Consideration");
                    System.out.println("-".repeat(60));

                    for (var assignment : assignments) {
                        FeatureNode feature = program.getFeature(assignment.getFeatureId());
                        if (feature != null) {
                            double calculatedScore = feature.calculatePriorityScore(
                                    release.getDaysUntilDeadline(),
                                    release.getTeamCapacity()
                            );

                            String consideration = assignment.getConsideration() != null ?
                                    assignment.getConsideration() : "Auto-assigned";
                            // Truncate long considerations
                            if (consideration.length() > 20) {
                                consideration = consideration.substring(0, 17) + "...";
                            }

                            System.out.printf("%-15s %-20.2f %-20s%n",
                                    assignment.getFeatureId(),
                                    calculatedScore,
                                    consideration);
                        }
                    }
                }
            }

            // Capacity analysis
            System.out.println("\n" + CYAN + "CAPACITY ANALYSIS:" + RESET);
            double remaining = release.getRemainingCapacity();
            if (remaining >= 0) {
                System.out.printf(GREEN + "✓ Remaining capacity: %.1f story points%n" + RESET, remaining);
            } else {
                System.out.printf(YELLOW + "! Over capacity by: %.1f story points%n" + RESET, -remaining);
            }

            System.out.println(WHITE_BOLD + "=".repeat(80) + RESET);
        }
    }
}
