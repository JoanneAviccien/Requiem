package com.requiem.ast;

import java.util.List;
import java.util.ArrayList;

public class FeatureNode extends ASTNode {
    private String id;
    private String title;
    private int businessValue;  // 1-10
    private int effort;         // story points
    private int risk;           // 1-5
    private List<String> dependencies = new ArrayList<>();
    private double priorityScore;
    
    public FeatureNode(String id, String title, int value, int effort, int risk) {
        this.id = id;
        this.title = title;
        this.businessValue = value;
        this.effort = effort;
        this.risk = risk;
    }
    
    // Calculate priority based on time frame, progress, and feature weight
    public double calculatePriorityScore(int daysUntilDeadline, int teamVelocity) {
        double urgency = calculateUrgency(daysUntilDeadline);
        double feasibility = calculateFeasibility(teamVelocity);
        
        // Priority formula: (Value × Urgency × Feasibility) / (Effort × Risk)
        this.priorityScore = (businessValue * urgency * feasibility) / (effort * risk);
        return priorityScore;
    }
    
    private double calculateUrgency(int daysUntilDeadline) {
        if (daysUntilDeadline <= 7) return 3.0;
        if (daysUntilDeadline <= 14) return 2.0;
        if (daysUntilDeadline <= 30) return 1.5;
        return 1.0;
    }
    
    private double calculateFeasibility(int teamVelocity) {
        double relativeEffort = (double) effort / teamVelocity;
        if (relativeEffort <= 0.5) return 1.0;
        if (relativeEffort <= 1.0) return 0.8;
        if (relativeEffort <= 2.0) return 0.5;
        return 0.2;
    }
    
    // Getters and setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getBusinessValue() { return businessValue; }
    public int getEffort() { return effort; }
    public int getRisk() { return risk; }
    public List<String> getDependencies() { return dependencies; }
    public double getPriorityScore() { return priorityScore; }
    
    public void addDependency(String featureId) {
        dependencies.add(featureId);
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
