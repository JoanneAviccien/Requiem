// Generated from RequiemV2.g4 by ANTLR 4.13.2
package com.requiem.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RequiemV2Parser}.
 */
public interface RequiemV2Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RequiemV2Parser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(RequiemV2Parser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequiemV2Parser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(RequiemV2Parser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequiemV2Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(RequiemV2Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequiemV2Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(RequiemV2Parser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequiemV2Parser#featureDecl}.
	 * @param ctx the parse tree
	 */
	void enterFeatureDecl(RequiemV2Parser.FeatureDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequiemV2Parser#featureDecl}.
	 * @param ctx the parse tree
	 */
	void exitFeatureDecl(RequiemV2Parser.FeatureDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequiemV2Parser#releasePlan}.
	 * @param ctx the parse tree
	 */
	void enterReleasePlan(RequiemV2Parser.ReleasePlanContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequiemV2Parser#releasePlan}.
	 * @param ctx the parse tree
	 */
	void exitReleasePlan(RequiemV2Parser.ReleasePlanContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequiemV2Parser#categoryAllocation}.
	 * @param ctx the parse tree
	 */
	void enterCategoryAllocation(RequiemV2Parser.CategoryAllocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequiemV2Parser#categoryAllocation}.
	 * @param ctx the parse tree
	 */
	void exitCategoryAllocation(RequiemV2Parser.CategoryAllocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequiemV2Parser#featureAssignment}.
	 * @param ctx the parse tree
	 */
	void enterFeatureAssignment(RequiemV2Parser.FeatureAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequiemV2Parser#featureAssignment}.
	 * @param ctx the parse tree
	 */
	void exitFeatureAssignment(RequiemV2Parser.FeatureAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequiemV2Parser#moscowCategory}.
	 * @param ctx the parse tree
	 */
	void enterMoscowCategory(RequiemV2Parser.MoscowCategoryContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequiemV2Parser#moscowCategory}.
	 * @param ctx the parse tree
	 */
	void exitMoscowCategory(RequiemV2Parser.MoscowCategoryContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequiemV2Parser#idList}.
	 * @param ctx the parse tree
	 */
	void enterIdList(RequiemV2Parser.IdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequiemV2Parser#idList}.
	 * @param ctx the parse tree
	 */
	void exitIdList(RequiemV2Parser.IdListContext ctx);
}