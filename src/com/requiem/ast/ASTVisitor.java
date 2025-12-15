package com.requiem.ast;

public interface ASTVisitor<T> {
    T visit(com.requiem.ast.ProgramNode node);
    T visit(com.requiem.ast.FeatureNode node);
    T visit(ReleasePlanNode node);
}
