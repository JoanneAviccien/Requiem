// Generated from RequiemV2.g4 by ANTLR 4.13.2
package com.requiem.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RequiemV2Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RequiemV2Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RequiemV2Parser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(RequiemV2Parser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequiemV2Parser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(RequiemV2Parser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequiemV2Parser#featureDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeatureDecl(RequiemV2Parser.FeatureDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequiemV2Parser#releasePlan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReleasePlan(RequiemV2Parser.ReleasePlanContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequiemV2Parser#categoryAllocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCategoryAllocation(RequiemV2Parser.CategoryAllocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequiemV2Parser#featureAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFeatureAssignment(RequiemV2Parser.FeatureAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequiemV2Parser#moscowCategory}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoscowCategory(RequiemV2Parser.MoscowCategoryContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequiemV2Parser#idList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdList(RequiemV2Parser.IdListContext ctx);
}