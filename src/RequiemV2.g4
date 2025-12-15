grammar RequiemV2;

// Keywords
FEATURE     : 'feature';
RELEASE     : 'release';
ASSIGN      : 'assign';
MUST        : 'must';
SHOULD      : 'should';
COULD       : 'could';
WONT        : 'wont';

// Attribute keywords
TITLE       : 'title:';
VALUE       : 'value:';
EFFORT      : 'effort:';
RISK        : 'risk:';
DEPENDENCIES: 'dependencies:';
TARGET_DATE : 'targetDate:';
TEAM_CAPACITY: 'teamCapacity:';
CURRENT_PROGRESS: 'currentProgress:';
PRIORITY_SCORE: 'priorityScore:';

// Identifiers and literals
ID          : [a-zA-Z_][a-zA-Z0-9_-]*;
STRING      : '"' (~["\r\n] | '\\"')* '"';
INT         : [0-9]+;
FLOAT       : [0-9]+ '.' [0-9]+;
DATE        : [0-9][0-9]? '-' [0-9][0-9]? '-' [0-9][0-9][0-9][0-9];

// Symbols
LPAREN      : '(';
RPAREN      : ')';
LBRACE      : '{';
RBRACE      : '}';
COMMA       : ',';
SEMI        : ';';
COLON       : ':';

// Whitespace and comments
WS          : [ \t\r\n]+ -> skip;
COMMENT     : '#' ~[\r\n]* -> skip;
BLOCK_COMMENT : '/*' .*? '*/' -> skip;

// Parser rules
program     : statement* EOF;

statement   : featureDecl | releasePlan | COMMENT | BLOCK_COMMENT;

featureDecl : FEATURE ID LPAREN
              TITLE STRING COMMA
              VALUE (INT | FLOAT) COMMA
              EFFORT (INT | FLOAT) COMMA
              RISK (INT | FLOAT)
              (COMMA DEPENDENCIES idList)?
              RPAREN SEMI;

releasePlan : RELEASE ID LPAREN
              TARGET_DATE DATE COMMA
              TEAM_CAPACITY (INT | FLOAT) COMMA
              CURRENT_PROGRESS (INT | FLOAT)
              RPAREN
              LBRACE
                categoryAllocation+
              RBRACE SEMI;

categoryAllocation : moscowCategory LBRACE
                      featureAssignment*
                    RBRACE SEMI;

featureAssignment : ASSIGN ID (COMMA PRIORITY_SCORE (INT | FLOAT))? SEMI;

moscowCategory : MUST | SHOULD | COULD | WONT;

idList      : ID (COMMA ID)*;
