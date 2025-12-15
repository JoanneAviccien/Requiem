package com.requiem.builder;

import com.requiem.ast.*;
import com.requiem.parser.RequiemV2BaseListener;
import com.requiem.parser.RequiemV2Parser;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.Stack;

public class ASTBuilder extends RequiemV2BaseListener {
    private ProgramNode program;
    private Stack<ASTNode> nodeStack = new Stack<>();
    private FeatureNode currentFeature;
    private ReleasePlanNode currentReleasePlan;
    private ReleasePlanNode.MoscowCategory currentCategory;

    public ASTBuilder() {
        program = new ProgramNode();
        nodeStack.push(program);
    }

    @Override
    public void enterFeatureDecl(RequiemV2Parser.FeatureDeclContext ctx) {
        String id = ctx.ID().getText();
        String title = ctx.STRING().getText().replaceAll("\"", "");
        int value = Integer.parseInt(ctx.INT(0).getText());
        int effort = Integer.parseInt(ctx.INT(1).getText());
        int risk = Integer.parseInt(ctx.INT(2).getText());
        
        currentFeature = new FeatureNode(id, title, value, effort, risk);
        currentFeature.setLine(ctx.start.getLine());
        currentFeature.setColumn(ctx.start.getCharPositionInLine());
    }
    
    @Override
    public void exitFeatureDecl(RequiemV2Parser.FeatureDeclContext ctx) {
        if (ctx.idList() != null) {
            for (var idCtx : ctx.idList().ID()) {
                currentFeature.addDependency(idCtx.getText());
            }
        }
        
        program.addFeature(currentFeature);
        currentFeature = null;
    }
    
    @Override
    public void enterReleasePlan(RequiemV2Parser.ReleasePlanContext ctx) {
        String id = ctx.ID().getText();
        String date = ctx.DATE().getText();
        int capacity = Integer.parseInt(ctx.INT(0).getText());
        int progress = Integer.parseInt(ctx.INT(1).getText());
        
        currentReleasePlan = new ReleasePlanNode(id, date, capacity, progress);
        currentReleasePlan.setLine(ctx.start.getLine());
        currentReleasePlan.setColumn(ctx.start.getCharPositionInLine());
    }
    
    @Override
    public void exitReleasePlan(RequiemV2Parser.ReleasePlanContext ctx) {
        program.addReleasePlan(currentReleasePlan);
        currentReleasePlan = null;
    }
    
    @Override
    public void enterMoscowCategory(RequiemV2Parser.MoscowCategoryContext ctx) {
        switch (ctx.getText().toLowerCase()) {
            case "must":
                currentCategory = ReleasePlanNode.MoscowCategory.MUST;
                break;
            case "should":
                currentCategory = ReleasePlanNode.MoscowCategory.SHOULD;
                break;
            case "could":
                currentCategory = ReleasePlanNode.MoscowCategory.COULD;
                break;
            case "wont":
                currentCategory = ReleasePlanNode.MoscowCategory.WONT;
                break;
        }
    }
    
    @Override
    public void enterFeatureAssignment(RequiemV2Parser.FeatureAssignmentContext ctx) {
        String featureId = ctx.ID().getText();
        double priority = 0.0;
        
        if (ctx.FLOAT() != null) {
            priority = Double.parseDouble(ctx.FLOAT().getText());
        } else {
            // Auto-calculate priority if not specified
            FeatureNode feature = program.getFeature(featureId);
            if (feature != null && currentReleasePlan != null) {
                int daysUntilDeadline = currentReleasePlan.getDaysUntilDeadline();
                priority = feature.calculatePriorityScore(daysUntilDeadline, 
                                                         currentReleasePlan.getTeamCapacity());
            }
        }
        
        if (currentReleasePlan != null && currentCategory != null) {
            currentReleasePlan.assignFeature(currentCategory, featureId, priority);
        }
    }
    
    public ProgramNode getProgram() {
        return program;
    }
}

