// Generated from RequiemV2.g4 by ANTLR 4.13.2
package com.requiem.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class RequiemV2Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FEATURE=1, RELEASE=2, ASSIGN=3, MUST=4, SHOULD=5, COULD=6, WONT=7, TITLE=8, 
		VALUE=9, EFFORT=10, RISK=11, DEPENDENCIES=12, TARGET_DATE=13, TEAM_CAPACITY=14, 
		CURRENT_PROGRESS=15, PRIORITY_SCORE=16, ID=17, STRING=18, INT=19, FLOAT=20, 
		DATE=21, LPAREN=22, RPAREN=23, LBRACE=24, RBRACE=25, COMMA=26, SEMI=27, 
		COLON=28, WS=29, COMMENT=30, BLOCK_COMMENT=31;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_featureDecl = 2, RULE_releasePlan = 3, 
		RULE_categoryAllocation = 4, RULE_featureAssignment = 5, RULE_moscowCategory = 6, 
		RULE_idList = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "featureDecl", "releasePlan", "categoryAllocation", 
			"featureAssignment", "moscowCategory", "idList"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'feature'", "'release'", "'assign'", "'must'", "'should'", "'could'", 
			"'wont'", "'title:'", "'value:'", "'effort:'", "'risk:'", "'dependencies:'", 
			"'targetDate:'", "'teamCapacity:'", "'currentProgress:'", "'priorityScore:'", 
			null, null, null, null, null, "'('", "')'", "'{'", "'}'", "','", "';'", 
			"':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FEATURE", "RELEASE", "ASSIGN", "MUST", "SHOULD", "COULD", "WONT", 
			"TITLE", "VALUE", "EFFORT", "RISK", "DEPENDENCIES", "TARGET_DATE", "TEAM_CAPACITY", 
			"CURRENT_PROGRESS", "PRIORITY_SCORE", "ID", "STRING", "INT", "FLOAT", 
			"DATE", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "COMMA", "SEMI", "COLON", 
			"WS", "COMMENT", "BLOCK_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "RequiemV2.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RequiemV2Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(RequiemV2Parser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequiemV2Visitor ) return ((RequiemV2Visitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3221225478L) != 0)) {
				{
				{
				setState(16);
				statement();
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(22);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public FeatureDeclContext featureDecl() {
			return getRuleContext(FeatureDeclContext.class,0);
		}
		public ReleasePlanContext releasePlan() {
			return getRuleContext(ReleasePlanContext.class,0);
		}
		public TerminalNode COMMENT() { return getToken(RequiemV2Parser.COMMENT, 0); }
		public TerminalNode BLOCK_COMMENT() { return getToken(RequiemV2Parser.BLOCK_COMMENT, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequiemV2Visitor ) return ((RequiemV2Visitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(28);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FEATURE:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				featureDecl();
				}
				break;
			case RELEASE:
				enterOuterAlt(_localctx, 2);
				{
				setState(25);
				releasePlan();
				}
				break;
			case COMMENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(26);
				match(COMMENT);
				}
				break;
			case BLOCK_COMMENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(27);
				match(BLOCK_COMMENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureDeclContext extends ParserRuleContext {
		public TerminalNode FEATURE() { return getToken(RequiemV2Parser.FEATURE, 0); }
		public TerminalNode ID() { return getToken(RequiemV2Parser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(RequiemV2Parser.LPAREN, 0); }
		public TerminalNode TITLE() { return getToken(RequiemV2Parser.TITLE, 0); }
		public TerminalNode STRING() { return getToken(RequiemV2Parser.STRING, 0); }
		public List<TerminalNode> COMMA() { return getTokens(RequiemV2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RequiemV2Parser.COMMA, i);
		}
		public TerminalNode VALUE() { return getToken(RequiemV2Parser.VALUE, 0); }
		public TerminalNode EFFORT() { return getToken(RequiemV2Parser.EFFORT, 0); }
		public TerminalNode RISK() { return getToken(RequiemV2Parser.RISK, 0); }
		public TerminalNode RPAREN() { return getToken(RequiemV2Parser.RPAREN, 0); }
		public TerminalNode SEMI() { return getToken(RequiemV2Parser.SEMI, 0); }
		public List<TerminalNode> INT() { return getTokens(RequiemV2Parser.INT); }
		public TerminalNode INT(int i) {
			return getToken(RequiemV2Parser.INT, i);
		}
		public List<TerminalNode> FLOAT() { return getTokens(RequiemV2Parser.FLOAT); }
		public TerminalNode FLOAT(int i) {
			return getToken(RequiemV2Parser.FLOAT, i);
		}
		public TerminalNode DEPENDENCIES() { return getToken(RequiemV2Parser.DEPENDENCIES, 0); }
		public IdListContext idList() {
			return getRuleContext(IdListContext.class,0);
		}
		public FeatureDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_featureDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).enterFeatureDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).exitFeatureDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequiemV2Visitor ) return ((RequiemV2Visitor<? extends T>)visitor).visitFeatureDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeatureDeclContext featureDecl() throws RecognitionException {
		FeatureDeclContext _localctx = new FeatureDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_featureDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(FEATURE);
			setState(31);
			match(ID);
			setState(32);
			match(LPAREN);
			setState(33);
			match(TITLE);
			setState(34);
			match(STRING);
			setState(35);
			match(COMMA);
			setState(36);
			match(VALUE);
			setState(37);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(38);
			match(COMMA);
			setState(39);
			match(EFFORT);
			setState(40);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(41);
			match(COMMA);
			setState(42);
			match(RISK);
			setState(43);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(44);
				match(COMMA);
				setState(45);
				match(DEPENDENCIES);
				setState(46);
				idList();
				}
			}

			setState(49);
			match(RPAREN);
			setState(50);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReleasePlanContext extends ParserRuleContext {
		public TerminalNode RELEASE() { return getToken(RequiemV2Parser.RELEASE, 0); }
		public TerminalNode ID() { return getToken(RequiemV2Parser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(RequiemV2Parser.LPAREN, 0); }
		public TerminalNode TARGET_DATE() { return getToken(RequiemV2Parser.TARGET_DATE, 0); }
		public TerminalNode DATE() { return getToken(RequiemV2Parser.DATE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(RequiemV2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RequiemV2Parser.COMMA, i);
		}
		public TerminalNode TEAM_CAPACITY() { return getToken(RequiemV2Parser.TEAM_CAPACITY, 0); }
		public TerminalNode CURRENT_PROGRESS() { return getToken(RequiemV2Parser.CURRENT_PROGRESS, 0); }
		public TerminalNode RPAREN() { return getToken(RequiemV2Parser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(RequiemV2Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(RequiemV2Parser.RBRACE, 0); }
		public TerminalNode SEMI() { return getToken(RequiemV2Parser.SEMI, 0); }
		public List<TerminalNode> INT() { return getTokens(RequiemV2Parser.INT); }
		public TerminalNode INT(int i) {
			return getToken(RequiemV2Parser.INT, i);
		}
		public List<TerminalNode> FLOAT() { return getTokens(RequiemV2Parser.FLOAT); }
		public TerminalNode FLOAT(int i) {
			return getToken(RequiemV2Parser.FLOAT, i);
		}
		public List<CategoryAllocationContext> categoryAllocation() {
			return getRuleContexts(CategoryAllocationContext.class);
		}
		public CategoryAllocationContext categoryAllocation(int i) {
			return getRuleContext(CategoryAllocationContext.class,i);
		}
		public ReleasePlanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_releasePlan; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).enterReleasePlan(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).exitReleasePlan(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequiemV2Visitor ) return ((RequiemV2Visitor<? extends T>)visitor).visitReleasePlan(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReleasePlanContext releasePlan() throws RecognitionException {
		ReleasePlanContext _localctx = new ReleasePlanContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_releasePlan);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(RELEASE);
			setState(53);
			match(ID);
			setState(54);
			match(LPAREN);
			setState(55);
			match(TARGET_DATE);
			setState(56);
			match(DATE);
			setState(57);
			match(COMMA);
			setState(58);
			match(TEAM_CAPACITY);
			setState(59);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(60);
			match(COMMA);
			setState(61);
			match(CURRENT_PROGRESS);
			setState(62);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(63);
			match(RPAREN);
			setState(64);
			match(LBRACE);
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				categoryAllocation();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 240L) != 0) );
			setState(70);
			match(RBRACE);
			setState(71);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CategoryAllocationContext extends ParserRuleContext {
		public MoscowCategoryContext moscowCategory() {
			return getRuleContext(MoscowCategoryContext.class,0);
		}
		public TerminalNode LBRACE() { return getToken(RequiemV2Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(RequiemV2Parser.RBRACE, 0); }
		public TerminalNode SEMI() { return getToken(RequiemV2Parser.SEMI, 0); }
		public List<FeatureAssignmentContext> featureAssignment() {
			return getRuleContexts(FeatureAssignmentContext.class);
		}
		public FeatureAssignmentContext featureAssignment(int i) {
			return getRuleContext(FeatureAssignmentContext.class,i);
		}
		public CategoryAllocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_categoryAllocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).enterCategoryAllocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).exitCategoryAllocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequiemV2Visitor ) return ((RequiemV2Visitor<? extends T>)visitor).visitCategoryAllocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CategoryAllocationContext categoryAllocation() throws RecognitionException {
		CategoryAllocationContext _localctx = new CategoryAllocationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_categoryAllocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			moscowCategory();
			setState(74);
			match(LBRACE);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ASSIGN) {
				{
				{
				setState(75);
				featureAssignment();
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(81);
			match(RBRACE);
			setState(82);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureAssignmentContext extends ParserRuleContext {
		public TerminalNode ASSIGN() { return getToken(RequiemV2Parser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(RequiemV2Parser.ID, 0); }
		public TerminalNode SEMI() { return getToken(RequiemV2Parser.SEMI, 0); }
		public TerminalNode COMMA() { return getToken(RequiemV2Parser.COMMA, 0); }
		public TerminalNode PRIORITY_SCORE() { return getToken(RequiemV2Parser.PRIORITY_SCORE, 0); }
		public TerminalNode INT() { return getToken(RequiemV2Parser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(RequiemV2Parser.FLOAT, 0); }
		public FeatureAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_featureAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).enterFeatureAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).exitFeatureAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequiemV2Visitor ) return ((RequiemV2Visitor<? extends T>)visitor).visitFeatureAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeatureAssignmentContext featureAssignment() throws RecognitionException {
		FeatureAssignmentContext _localctx = new FeatureAssignmentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_featureAssignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(ASSIGN);
			setState(85);
			match(ID);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(86);
				match(COMMA);
				setState(87);
				match(PRIORITY_SCORE);
				setState(88);
				_la = _input.LA(1);
				if ( !(_la==INT || _la==FLOAT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(91);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MoscowCategoryContext extends ParserRuleContext {
		public TerminalNode MUST() { return getToken(RequiemV2Parser.MUST, 0); }
		public TerminalNode SHOULD() { return getToken(RequiemV2Parser.SHOULD, 0); }
		public TerminalNode COULD() { return getToken(RequiemV2Parser.COULD, 0); }
		public TerminalNode WONT() { return getToken(RequiemV2Parser.WONT, 0); }
		public MoscowCategoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_moscowCategory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).enterMoscowCategory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).exitMoscowCategory(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequiemV2Visitor ) return ((RequiemV2Visitor<? extends T>)visitor).visitMoscowCategory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MoscowCategoryContext moscowCategory() throws RecognitionException {
		MoscowCategoryContext _localctx = new MoscowCategoryContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_moscowCategory);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 240L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdListContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(RequiemV2Parser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RequiemV2Parser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(RequiemV2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RequiemV2Parser.COMMA, i);
		}
		public IdListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).enterIdList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequiemV2Listener ) ((RequiemV2Listener)listener).exitIdList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequiemV2Visitor ) return ((RequiemV2Visitor<? extends T>)visitor).visitIdList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdListContext idList() throws RecognitionException {
		IdListContext _localctx = new IdListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(ID);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(96);
				match(COMMA);
				setState(97);
				match(ID);
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001fh\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0001"+
		"\u0000\u0005\u0000\u0012\b\u0000\n\u0000\f\u0000\u0015\t\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\u001d\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u00020\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0004\u0003C\b\u0003\u000b\u0003\f\u0003D\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004M\b"+
		"\u0004\n\u0004\f\u0004P\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005Z\b"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0005\u0007c\b\u0007\n\u0007\f\u0007f\t\u0007\u0001"+
		"\u0007\u0000\u0000\b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0000\u0002\u0001"+
		"\u0000\u0013\u0014\u0001\u0000\u0004\u0007h\u0000\u0013\u0001\u0000\u0000"+
		"\u0000\u0002\u001c\u0001\u0000\u0000\u0000\u0004\u001e\u0001\u0000\u0000"+
		"\u0000\u00064\u0001\u0000\u0000\u0000\bI\u0001\u0000\u0000\u0000\nT\u0001"+
		"\u0000\u0000\u0000\f]\u0001\u0000\u0000\u0000\u000e_\u0001\u0000\u0000"+
		"\u0000\u0010\u0012\u0003\u0002\u0001\u0000\u0011\u0010\u0001\u0000\u0000"+
		"\u0000\u0012\u0015\u0001\u0000\u0000\u0000\u0013\u0011\u0001\u0000\u0000"+
		"\u0000\u0013\u0014\u0001\u0000\u0000\u0000\u0014\u0016\u0001\u0000\u0000"+
		"\u0000\u0015\u0013\u0001\u0000\u0000\u0000\u0016\u0017\u0005\u0000\u0000"+
		"\u0001\u0017\u0001\u0001\u0000\u0000\u0000\u0018\u001d\u0003\u0004\u0002"+
		"\u0000\u0019\u001d\u0003\u0006\u0003\u0000\u001a\u001d\u0005\u001e\u0000"+
		"\u0000\u001b\u001d\u0005\u001f\u0000\u0000\u001c\u0018\u0001\u0000\u0000"+
		"\u0000\u001c\u0019\u0001\u0000\u0000\u0000\u001c\u001a\u0001\u0000\u0000"+
		"\u0000\u001c\u001b\u0001\u0000\u0000\u0000\u001d\u0003\u0001\u0000\u0000"+
		"\u0000\u001e\u001f\u0005\u0001\u0000\u0000\u001f \u0005\u0011\u0000\u0000"+
		" !\u0005\u0016\u0000\u0000!\"\u0005\b\u0000\u0000\"#\u0005\u0012\u0000"+
		"\u0000#$\u0005\u001a\u0000\u0000$%\u0005\t\u0000\u0000%&\u0007\u0000\u0000"+
		"\u0000&\'\u0005\u001a\u0000\u0000\'(\u0005\n\u0000\u0000()\u0007\u0000"+
		"\u0000\u0000)*\u0005\u001a\u0000\u0000*+\u0005\u000b\u0000\u0000+/\u0007"+
		"\u0000\u0000\u0000,-\u0005\u001a\u0000\u0000-.\u0005\f\u0000\u0000.0\u0003"+
		"\u000e\u0007\u0000/,\u0001\u0000\u0000\u0000/0\u0001\u0000\u0000\u0000"+
		"01\u0001\u0000\u0000\u000012\u0005\u0017\u0000\u000023\u0005\u001b\u0000"+
		"\u00003\u0005\u0001\u0000\u0000\u000045\u0005\u0002\u0000\u000056\u0005"+
		"\u0011\u0000\u000067\u0005\u0016\u0000\u000078\u0005\r\u0000\u000089\u0005"+
		"\u0015\u0000\u00009:\u0005\u001a\u0000\u0000:;\u0005\u000e\u0000\u0000"+
		";<\u0007\u0000\u0000\u0000<=\u0005\u001a\u0000\u0000=>\u0005\u000f\u0000"+
		"\u0000>?\u0007\u0000\u0000\u0000?@\u0005\u0017\u0000\u0000@B\u0005\u0018"+
		"\u0000\u0000AC\u0003\b\u0004\u0000BA\u0001\u0000\u0000\u0000CD\u0001\u0000"+
		"\u0000\u0000DB\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EF\u0001"+
		"\u0000\u0000\u0000FG\u0005\u0019\u0000\u0000GH\u0005\u001b\u0000\u0000"+
		"H\u0007\u0001\u0000\u0000\u0000IJ\u0003\f\u0006\u0000JN\u0005\u0018\u0000"+
		"\u0000KM\u0003\n\u0005\u0000LK\u0001\u0000\u0000\u0000MP\u0001\u0000\u0000"+
		"\u0000NL\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000OQ\u0001\u0000"+
		"\u0000\u0000PN\u0001\u0000\u0000\u0000QR\u0005\u0019\u0000\u0000RS\u0005"+
		"\u001b\u0000\u0000S\t\u0001\u0000\u0000\u0000TU\u0005\u0003\u0000\u0000"+
		"UY\u0005\u0011\u0000\u0000VW\u0005\u001a\u0000\u0000WX\u0005\u0010\u0000"+
		"\u0000XZ\u0007\u0000\u0000\u0000YV\u0001\u0000\u0000\u0000YZ\u0001\u0000"+
		"\u0000\u0000Z[\u0001\u0000\u0000\u0000[\\\u0005\u001b\u0000\u0000\\\u000b"+
		"\u0001\u0000\u0000\u0000]^\u0007\u0001\u0000\u0000^\r\u0001\u0000\u0000"+
		"\u0000_d\u0005\u0011\u0000\u0000`a\u0005\u001a\u0000\u0000ac\u0005\u0011"+
		"\u0000\u0000b`\u0001\u0000\u0000\u0000cf\u0001\u0000\u0000\u0000db\u0001"+
		"\u0000\u0000\u0000de\u0001\u0000\u0000\u0000e\u000f\u0001\u0000\u0000"+
		"\u0000fd\u0001\u0000\u0000\u0000\u0007\u0013\u001c/DNYd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}