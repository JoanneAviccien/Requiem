package com.requiem.error;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import java.util.regex.Pattern;

public class RequiemLexerErrorListener extends BaseErrorListener {

    private final Pattern ID_PATTERN = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_-]*");
    private final Pattern DATE_PATTERN = Pattern.compile("\\d{1,2}-\\d{1,2}-\\d{4}");
    private final Pattern INT_PATTERN = Pattern.compile("\\d+");
    private final Pattern FLOAT_PATTERN = Pattern.compile("\\d+\\.\\d+");

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {

        String tokenText = "";
        if (offendingSymbol instanceof Token) {
            Token token = (Token) offendingSymbol;
            tokenText = token.getText() != null ? token.getText() : "";
        }

        String errorMsg;

        // Handle EOF
        if (tokenText.isEmpty() && msg.contains("EOF")) {
            errorMsg = String.format("Line %d:%d - Unexpected end of file", line, charPositionInLine + 1);
        }
        // Handle token mismatches
        else if (msg.contains("mismatched input") || msg.contains("extraneous input")) {
            errorMsg = handleTokenMismatch(tokenText, line, charPositionInLine, msg);
        }
        // Handle no viable alternative
        else if (msg.contains("no viable alternative")) {
            errorMsg = String.format("Line %d:%d - Unexpected token '%s'",
                    line, charPositionInLine + 1, tokenText);
        }
        // Handle token recognition errors (invalid characters)
        else if (msg.contains("token recognition error")) {
            errorMsg = handleTokenRecognitionError(tokenText, line, charPositionInLine, msg);
        }
        // Handle other syntax errors
        else {
            errorMsg = String.format("Line %d:%d - %s", line, charPositionInLine + 1, msg);
        }

        throw new ParseCancellationException(errorMsg);
    }

    private String handleTokenMismatch(String tokenText, int line, int charPos, String msg) {
        // Check what kind of token was expected based on the error message
        if (msg.contains("ID")) {
            return formatIDError(tokenText, line, charPos, msg);
        } else if (msg.contains("STRING")) {
            return formatStringError(tokenText, line, charPos, msg);
        } else if (msg.contains("DATE")) {
            return formatDateError(tokenText, line, charPos, msg);
        } else if (msg.contains("INT") || msg.contains("FLOAT") || msg.contains("NUMERAL")) {
            return formatNumberError(tokenText, line, charPos, msg);
        } else if (msg.contains("TITLE") || msg.contains("VALUE") || msg.contains("EFFORT") ||
                msg.contains("RISK") || msg.contains("DEPENDENCIES") || msg.contains("TARGET_DATE") ||
                msg.contains("TEAM_CAPACITY") || msg.contains("CURRENT_PROGRESS") ||
                msg.contains("PRIORITY_SCORE")) {
            return formatKeywordError(tokenText, line, charPos, msg);
        } else if (msg.contains("FEATURE") || msg.contains("RELEASE") || msg.contains("ASSIGN") ||
                msg.contains("MUST") || msg.contains("SHOULD") || msg.contains("COULD") ||
                msg.contains("WONT")) {
            return formatReservedWordError(tokenText, line, charPos, msg);
        } else {
            return formatGenericError(tokenText, line, charPos, msg);
        }
    }

    private String formatIDError(String tokenText, int line, int charPos, String msg) {
        if (tokenText.matches("^[0-9].*")) {
            return String.format("Line %d:%d - Identifiers cannot start with numbers: '%s'",
                    line, charPos + 1, tokenText);
        } else if (tokenText.contains(" ")) {
            return String.format("Line %d:%d - Identifiers cannot contain spaces: '%s'",
                    line, charPos + 1, tokenText);
        } else if (!ID_PATTERN.matcher(tokenText).matches()) {
            return String.format("Line %d:%d - Invalid identifier format: '%s' (must match [a-zA-Z_][a-zA-Z0-9_-]*)",
                    line, charPos + 1, tokenText);
        } else {
            return String.format("Line %d:%d - %s: '%s'", line, charPos + 1, msg, tokenText);
        }
    }

    private String formatStringError(String tokenText, int line, int charPos, String msg) {
        if (tokenText.startsWith("\"") && !tokenText.endsWith("\"")) {
            return String.format("Line %d:%d - Unclosed string literal", line, charPos + 1);
        } else if (tokenText.contains("\n") || tokenText.contains("\r")) {
            return String.format("Line %d:%d - String literals cannot contain newlines", line, charPos + 1);
        } else if (!tokenText.startsWith("\"")) {
            return String.format("Line %d:%d - String must start with '\"'", line, charPos + 1);
        } else {
            return String.format("Line %d:%d - Invalid string literal: %s", line, charPos + 1, tokenText);
        }
    }

    private String formatNumberError(String tokenText, int line, int charPos, String msg) {
        // Check if it's a valid number (INT or FLOAT)
        if (!INT_PATTERN.matcher(tokenText).matches() && !FLOAT_PATTERN.matcher(tokenText).matches()) {
            return String.format("Line %d:%d - Invalid number format: '%s' (expected integer or floating point number like 10 or 5.5)",
                    line, charPos + 1, tokenText);
        }

        // For FLOAT validation
        if (tokenText.contains(".")) {
            if (!FLOAT_PATTERN.matcher(tokenText).matches()) {
                return String.format("Line %d:%d - Invalid float format: '%s' (expected format like 3.14)",
                        line, charPos + 1, tokenText);
            }
        }

        return String.format("Line %d:%d - %s: '%s'", line, charPos + 1, msg, tokenText);
    }

    private String formatDateError(String tokenText, int line, int charPos, String msg) {
        if (!DATE_PATTERN.matcher(tokenText).matches()) {
            return String.format("Line %d:%d - Invalid date format: '%s' (expected dd-mm-yyyy like 25-12-2023)",
                    line, charPos + 1, tokenText);
        }
        // Additional validation for valid date ranges
        try {
            String[] parts = tokenText.split("-");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (year < 1900 || year > 2100) {
                return String.format("Line %d:%d - Invalid year in date: '%s' (year must be between 1900-2100)",
                        line, charPos + 1, tokenText);
            }
            if (month < 1 || month > 12) {
                return String.format("Line %d:%d - Invalid month in date: '%s' (month must be 1-12)",
                        line, charPos + 1, tokenText);
            }
            if (day < 1 || day > 31) {
                return String.format("Line %d:%d - Invalid day in date: '%s' (day must be 1-31)",
                        line, charPos + 1, tokenText);
            }
        } catch (Exception e) {
            return String.format("Line %d:%d - Invalid date format: '%s' (expected dd-mm-yyyy)",
                    line, charPos + 1, tokenText);
        }
        return String.format("Line %d:%d - %s: '%s'", line, charPos + 1, msg, tokenText);
    }

    private String formatKeywordError(String tokenText, int line, int charPos, String msg) {
        // Check for common keyword errors
        if (msg.contains("TITLE")) {
            return String.format("Line %d:%d - Expected 'title:' but found '%s'",
                    line, charPos + 1, tokenText);
        } else if (msg.contains("VALUE")) {
            return String.format("Line %d:%d - Expected 'value:' but found '%s'",
                    line, charPos + 1, tokenText);
        } else if (msg.contains("EFFORT")) {
            return String.format("Line %d:%d - Expected 'effort:' but found '%s'",
                    line, charPos + 1, tokenText);
        } else if (msg.contains("RISK")) {
            return String.format("Line %d:%d - Expected 'risk:' but found '%s'",
                    line, charPos + 1, tokenText);
        } else if (msg.contains("TARGET_DATE")) {
            return String.format("Line %d:%d - Expected 'targetDate:' but found '%s'",
                    line, charPos + 1, tokenText);
        }
        return String.format("Line %d:%d - %s: '%s'", line, charPos + 1, msg, tokenText);
    }

    private String formatReservedWordError(String tokenText, int line, int charPos, String msg) {
        // Check for reserved word spelling
        String lowerToken = tokenText.toLowerCase();
        if (msg.contains("FEATURE") && !"feature".equals(lowerToken)) {
            return String.format("Line %d:%d - Expected 'feature' but found '%s'",
                    line, charPos + 1, tokenText);
        } else if (msg.contains("RELEASE") && !"release".equals(lowerToken)) {
            return String.format("Line %d:%d - Expected 'release' but found '%s'",
                    line, charPos + 1, tokenText);
        } else if (msg.contains("ASSIGN") && !"assign".equals(lowerToken)) {
            return String.format("Line %d:%d - Expected 'assign' but found '%s'",
                    line, charPos + 1, tokenText);
        } else if (msg.contains("MUST") && !"must".equals(lowerToken)) {
            return String.format("Line %d:%d - Expected 'must' but found '%s'",
                    line, charPos + 1, tokenText);
        } else if (msg.contains("SHOULD") && !"should".equals(lowerToken)) {
            return String.format("Line %d:%d - Expected 'should' but found '%s'",
                    line, charPos + 1, tokenText);
        } else if (msg.contains("COULD") && !"could".equals(lowerToken)) {
            return String.format("Line %d:%d - Expected 'could' but found '%s'",
                    line, charPos + 1, tokenText);
        } else if (msg.contains("WONT") && !"wont".equals(lowerToken)) {
            return String.format("Line %d:%d - Expected 'wont' but found '%s'",
                    line, charPos + 1, tokenText);
        }
        return String.format("Line %d:%d - %s: '%s'", line, charPos + 1, msg, tokenText);
    }

    private String handleTokenRecognitionError(String tokenText, int line, int charPos, String msg) {
        if (tokenText.length() == 1) {
            char c = tokenText.charAt(0);
            if (c == '@' || c == '!' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' ||
                    c == '+' || c == '=' || c == '[' || c == ']' || c == '|' || c == '\\' || c == '<' ||
                    c == '>' || c == '?' || c == '~' || c == '`') {
                return String.format("Line %d:%d - Invalid character '%c' (not allowed in RequiemV2)",
                        line, charPos + 1, c);
            }
        }
        return String.format("Line %d:%d - Invalid token '%s'", line, charPos + 1, tokenText);
    }

    private String formatGenericError(String tokenText, int line, int charPos, String msg) {
        // Check for common punctuation errors
        if (msg.contains("missing")) {
            // Extract what's missing from the error message
            if (msg.contains("'('")) {
                return String.format("Line %d:%d - Missing '(' after feature/release ID",
                        line, charPos + 1);
            } else if (msg.contains("')'")) {
                return String.format("Line %d:%d - Missing ')' at end of parameters",
                        line, charPos + 1);
            } else if (msg.contains("'{'")) {
                return String.format("Line %d:%d - Missing '{' after release plan header",
                        line, charPos + 1);
            } else if (msg.contains("'}'")) {
                return String.format("Line %d:%d - Missing '}' at end of release plan",
                        line, charPos + 1);
            } else if (msg.contains("';'")) {
                return String.format("Line %d:%d - Missing ';' at end of statement",
                        line, charPos + 1);
            } else if (msg.contains("','")) {
                return String.format("Line %d:%d - Missing ',' between parameters",
                        line, charPos + 1);
            } else if (msg.contains("':'")) {
                return String.format("Line %d:%d - Missing ':' after attribute keyword",
                        line, charPos + 1);
            }
        }

        return String.format("Line %d:%d - %s: '%s'", line, charPos + 1, msg, tokenText);
    }
}