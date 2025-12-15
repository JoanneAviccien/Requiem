package com.requiem.ast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ReleasePlanNode extends ASTNode {
    private String id;
    private LocalDate targetDate;
    private int teamCapacity;      // story points per sprint
    private int currentProgress;   // percentage
    private Map<MoscowCategory, List<FeatureAssignment>> allocations = new HashMap<>();
    
    public enum MoscowCategory {
        MUST("Must Have"),
        SHOULD("Should Have"),
        COULD("Could Have"),
        WONT("Won't Have");
        
        private final String label;
        
        MoscowCategory(String label) {
            this.label = label;
        }
        
        public String getLabel() { return label; }
    }
    
    public static class FeatureAssignment {
        private String featureId;
        private double assignedPriority;
        private String consideration;
        
        public FeatureAssignment(String featureId, double priority) {
            this.featureId = featureId;
            this.assignedPriority = priority;
        }
        
        // Getters and setters
        public String getFeatureId() { return featureId; }
        public double getAssignedPriority() { return assignedPriority; }
        public String getConsideration() { return consideration; }
        public void setConsideration(String consideration) { this.consideration = consideration; }
    }
    
    public ReleasePlanNode(String id, String dateStr, int capacity, int progress) {
        this.id = id;
        this.targetDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("d-M-yyyy"));
        this.teamCapacity = capacity;
        this.currentProgress = progress;
        
        // Initialize all categories
        for (MoscowCategory category : MoscowCategory.values()) {
            allocations.put(category, new ArrayList<>());
        }
    }
    
    public int getDaysUntilDeadline() {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), targetDate);
    }
    
    public double getRemainingCapacity() {
        double usedCapacity = allocations.values().stream()
            .flatMap(List::stream)
            .count(); // Assuming each feature uses avg 10 points
        return teamCapacity - usedCapacity;
    }
    
    public void assignFeature(MoscowCategory category, String featureId, double priority) {
        FeatureAssignment assignment = new FeatureAssignment(featureId, priority);
        allocations.get(category).add(assignment);
    }
    
    public void addConsideration(MoscowCategory category, String featureId, String consideration) {
        allocations.get(category).stream()
            .filter(a -> a.getFeatureId().equals(featureId))
            .findFirst()
            .ifPresent(a -> a.setConsideration(consideration));
    }
    
    // Getters
    public String getId() { return id; }
    public LocalDate getTargetDate() { return targetDate; }
    public int getTeamCapacity() { return teamCapacity; }
    public int getCurrentProgress() { return currentProgress; }
    public Map<MoscowCategory, List<FeatureAssignment>> getAllocations() { return allocations; }
    
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
