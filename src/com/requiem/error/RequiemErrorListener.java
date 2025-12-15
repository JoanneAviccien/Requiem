package com.requiem.error;

import com.requiem.parser.RequiemV2BaseListener;
import com.requiem.parser.RequiemV2Parser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class RequiemErrorListener extends RequiemV2BaseListener {

    private final Set<String> errorMessages = new HashSet<>();
    private final Set<String> definedFeatures = new HashSet<>();
    private final Set<String> definedReleasePlans = new HashSet<>();

    public RequiemErrorListener() {}

    @Override
    public void exitFeatureDecl(RequiemV2Parser.FeatureDeclContext ctx) {
        String featureId = ctx.ID().getText();

        // Check for duplicate feature IDs
        if (definedFeatures.contains(featureId)) {
            addError(ctx.ID().getSymbol(),
                    String.format("Duplicate feature ID '%s'", featureId));
        }
        definedFeatures.add(featureId);

        // Get INT and FLOAT lists
        List<TerminalNode> ints = ctx.INT();
        List<TerminalNode> floats = ctx.FLOAT();

        // BUSINESS VALUE validation (1-10) - first numeric value
        validateNumericValue(ints, floats, 0, 1, 10, "Business value");

        // EFFORT validation (non-negative) - second numeric value
        validateNumericValueNonNegative(ints, floats, 1, "Effort");

        // RISK validation (1-5) - third numeric value
        validateNumericValue(ints, floats, 2, 1, 5, "Risk");

        // Validate title string
        String title = ctx.STRING().getText();
        String cleanTitle = title.substring(1, title.length() - 1); // Remove quotes
        if (cleanTitle.trim().isEmpty()) {
            addError(ctx.STRING().getSymbol(),
                    "Title cannot be empty");
        }
    }

    private void validateNumericValue(List<TerminalNode> ints, List<TerminalNode> floats,
                                      int index, int min, int max, String fieldName) {
        // Check if we have enough INTs at this position
        if (ints != null && ints.size() > index) {
            TerminalNode node = ints.get(index);
            if (node != null) {
                String valueStr = node.getText();
                int value = parseInt(valueStr);
                if (value < min || value > max) {
                    addError(node.getSymbol(),
                            String.format("%s must be between %d-%d (got %d)", fieldName, min, max, value));
                }
                return; // INT found, no need to check FLOAT
            }
        }

        // Check if we have enough FLOATs at this position
        if (floats != null && floats.size() > index) {
            TerminalNode node = floats.get(index);
            if (node != null) {
                String valueStr = node.getText();
                double value = parseDouble(valueStr);
                if (value < min || value > max) {
                    addError(node.getSymbol(),
                            String.format("%s must be between %d-%d (got %.1f)", fieldName, min, max, value));
                }
            }
        }
    }

    private void validateNumericValueNonNegative(List<TerminalNode> ints, List<TerminalNode> floats,
                                                 int index, String fieldName) {
        if (ints != null && ints.size() > index) {
            TerminalNode node = ints.get(index);
            if (node != null) {
                String valueStr = node.getText();
                int value = parseInt(valueStr);
                if (value < 0) {
                    addError(node.getSymbol(),
                            String.format("%s must be non-negative (got %d)", fieldName, value));
                }
                return;
            }
        }

        if (floats != null && floats.size() > index) {
            TerminalNode node = floats.get(index);
            if (node != null) {
                String valueStr = node.getText();
                double value = parseDouble(valueStr);
                if (value < 0) {
                    addError(node.getSymbol(),
                            String.format("%s must be non-negative (got %.1f)", fieldName, value));
                }
            }
        }
    }

    @Override
    public void exitIdList(RequiemV2Parser.IdListContext ctx) {
        // Check for duplicate dependencies in the same list
        Set<String> seen = new HashSet<>();
        for (TerminalNode id : ctx.ID()) {
            String depId = id.getText();
            if (seen.contains(depId)) {
                addError(id.getSymbol(),
                        String.format("Duplicate dependency '%s' in the same list", depId));
            }
            seen.add(depId);

            // Check if dependency exists
            if (!definedFeatures.contains(depId)) {
                addError(id.getSymbol(),
                        String.format("Dependency '%s' refers to undefined feature", depId));
            }
        }
    }

    @Override
    public void exitReleasePlan(RequiemV2Parser.ReleasePlanContext ctx) {
        String releaseId = ctx.ID().getText();

        // Check for duplicate release plan IDs
        if (definedReleasePlans.contains(releaseId)) {
            addError(ctx.ID().getSymbol(),
                    String.format("Duplicate release plan ID '%s'", releaseId));
        }
        definedReleasePlans.add(releaseId);

        // Validate date format
        String dateStr = ctx.DATE().getText();
        if (!isValidDate(dateStr)) {
            addError(ctx.DATE().getSymbol(),
                    String.format("Invalid date format or value '%s' (expected dd-mm-yyyy)", dateStr));
        }

        // Get INT and FLOAT lists
        List<TerminalNode> ints = ctx.INT();
        List<TerminalNode> floats = ctx.FLOAT();

        // Validate capacity - first numeric value (must be positive)
        validatePositiveNumericValue(ints, floats, 0, "Team capacity");

        // Validate progress percentage - second numeric value (0-100)
        validateNumericValue(ints, floats, 1, 0, 100, "Current progress");
    }

    private void validatePositiveNumericValue(List<TerminalNode> ints, List<TerminalNode> floats,
                                              int index, String fieldName) {
        if (ints != null && ints.size() > index) {
            TerminalNode node = ints.get(index);
            if (node != null) {
                String valueStr = node.getText();
                int value = parseInt(valueStr);
                if (value <= 0) {
                    addError(node.getSymbol(),
                            String.format("%s must be positive (got %d)", fieldName, value));
                }
                return;
            }
        }

        if (floats != null && floats.size() > index) {
            TerminalNode node = floats.get(index);
            if (node != null) {
                String valueStr = node.getText();
                double value = parseDouble(valueStr);
                if (value <= 0) {
                    addError(node.getSymbol(),
                            String.format("%s must be positive (got %.1f)", fieldName, value));
                }
            }
        }
    }

    @Override
    public void exitFeatureAssignment(RequiemV2Parser.FeatureAssignmentContext ctx) {
        String featureId = ctx.ID().getText();

        // Check if feature exists
        if (!definedFeatures.contains(featureId)) {
            addError(ctx.ID().getSymbol(),
                    String.format("Undefined feature '%s' cannot be assigned to release plan", featureId));
        }

        // Validate priority score if provided
        // Priority score is optional, so we need to check if it exists
        List<TerminalNode> ints = Collections.singletonList(ctx.INT());
        List<TerminalNode> floats = Collections.singletonList(ctx.FLOAT());

        // Priority score is the first (and only) numeric value if provided
        if (ints != null && !ints.isEmpty()) {
            TerminalNode node = ints.get(0);
            if (node != null) {
                String priorityStr = node.getText();
                int priority = parseInt(priorityStr);
                if (priority < 0) {
                    addError(node.getSymbol(),
                            String.format("Priority score must be non-negative (got %d)", priority));
                }
            }
        } else if (floats != null && !floats.isEmpty()) {
            TerminalNode node = floats.get(0);
            if (node != null) {
                String priorityStr = node.getText();
                double priority = parseDouble(priorityStr);
                if (priority < 0.0) {
                    addError(node.getSymbol(),
                            String.format("Priority score must be non-negative (got %.2f)", priority));
                }
            }
        }
        // If neither INT nor FLOAT exists, that's fine - priority score is optional
    }

    // Helper method to parse number as double
    private double parseDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // Helper method to parse number as integer
    private int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private boolean isValidDate(String dateStr) {
        try {
            String[] parts = dateStr.split("-");
            if (parts.length != 3) return false;

            int day = parseInt(parts[0]);
            int month = parseInt(parts[1]);
            int year = parseInt(parts[2]);

            // Basic validation
            if (year < 1900 || year > 2100) return false;
            if (month < 1 || month > 12) return false;
            if (day < 1 || day > 31) return false;

            // Month-specific validation
            if (month == 2) {
                boolean isLeap = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                if (day > (isLeap ? 29 : 28)) return false;
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 30) return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void addError(Token token, String message) {
        if (token != null) {
            String errorMsg = String.format("Line %d:%d - %s",
                    token.getLine(),
                    token.getCharPositionInLine() + 1,
                    message);
            errorMessages.add(errorMsg);
        }
    }

    public Set<String> getErrorMessages() {
        return new HashSet<>(errorMessages);
    }

    public boolean hasErrors() {
        return !errorMessages.isEmpty();
    }

    public void throwIfErrors() {
        if (hasErrors()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Requiem V2 Semantic Errors:\n");
            sb.append("=".repeat(50)).append("\n");
            for (String error : errorMessages) {
                sb.append("• ").append(error).append("\n");
            }
            sb.append("=".repeat(50));
            throw new ParseCancellationException(sb.toString());
        }
    }
}