package com.requiem.ast;

import java.util.*;

public class ProgramNode extends ASTNode {
    private Map<String, FeatureNode> features = new HashMap<>();
    private List<ReleasePlanNode> releasePlans = new ArrayList<>();
    
    public void addFeature(FeatureNode feature) {
        features.put(feature.getId(), feature);
        addChild(feature);
    }
    
    public void addReleasePlan(ReleasePlanNode releasePlan) {
        releasePlans.add(releasePlan);
        addChild(releasePlan);
    }
    
    public FeatureNode getFeature(String id) {
        return features.get(id);
    }
    
    public List<ReleasePlanNode> getReleasePlans() {
        return releasePlans;
    }
    
    public Map<String, FeatureNode> getFeatures() {
        return Collections.unmodifiableMap(features);
    }
    
    public void validateDependencies() {
        for (FeatureNode feature : features.values()) {
            for (String depId : feature.getDependencies()) {
                if (!features.containsKey(depId)) {
                    System.err.printf("Warning: Feature %s depends on undefined feature %s%n", 
                                     feature.getId(), depId);
                }
            }
        }
    }
    
    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
