package com.requiem.ast;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode {
    protected int line;
    protected int column;
    protected List<ASTNode> children = new ArrayList<>();
    
    public abstract <T> T accept(com.requiem.ast.ASTVisitor<T> visitor);
    
    public void addChild(ASTNode node) {
        children.add(node);
    }
    
    // Getters and setters
    public int getLine() { return line; }
    public void setLine(int line) { this.line = line; }
    public int getColumn() { return column; }
    public void setColumn(int column) { this.column = column; }
}