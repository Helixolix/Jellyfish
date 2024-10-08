package com.helixo.syntax.lexer;

import com.helixo.syntax.expression.*;
import com.helixo.syntax.expression.impl.*;
import com.helixo.syntax.statement.*;
import com.helixo.syntax.statement.impl.*;
import com.helixo.syntax.statement.impl.exception.BreakStatement;
import com.helixo.syntax.statement.impl.exception.ContinueStatement;
import com.helixo.syntax.statement.impl.exception.ReturnStatement;
import com.helixo.syntax.tokens.Token;
import com.helixo.syntax.tokens.TokenType;

import java.util.ArrayList;
import java.util.List;

public final class Parser {

    private static final Token EOF = new Token(TokenType.EOF, ";");

    private final List<Token> tokens;
    private final int size;

    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        size = tokens.size();
    }

    public Statement parse() {
        final BlockStatement result = new BlockStatement();
        while (!match(TokenType.EOF)) {
            result.add(statement());
        }
        return result;
    }

    private Statement block() {
        final BlockStatement block = new BlockStatement();
        consume(TokenType.LBRACE);
        while (!match(TokenType.RBRACE)) {
            block.add(statement());
        }
        return block;
    }

    private Statement statementOrBlock() {
        if (get(0).getType() == TokenType.LBRACE) return block();
        return statement();
    }

    private Statement statement() {
        if (match(TokenType.WITHDRAW)) {
            return new PrintStatement(expression());
        }
        if (match(TokenType.WITHDRAWLN)) {
            return new PrintLnStatement(expression());
        }
        if (match(TokenType.IF)) {
            return ifElse();
        }
        if (match(TokenType.WHILE)) {
            return whileStatement();
        }
        if (match(TokenType.DO)) {
            return doWhileStatement();
        }
        if (match(TokenType.BREAK)) {
            return new BreakStatement();
        }
        if (match(TokenType.CONTINUE)) {
            return new ContinueStatement();
        }
        if (match(TokenType.RETURN)) {
            return new ReturnStatement(expression());
        }
        if (match(TokenType.FOR)) {
            return forStatement();
        }
        if (match(TokenType.METHOD)) {
            return functionMathod();
        }
        if (match(TokenType.TAKE) && match(TokenType.TWO_DOT)) {
            return takeStatement();
        }
        if (get(0).getType() == TokenType.WORD && get(1).getType() == TokenType.LPAREN) {
            return new FunctionalStatement(function());
        }
        return assignmentStatement();
    }

    private Statement takeStatement() {
        return new TakeStatement(expression());
    }

    private Statement assignmentStatement() {
        if (lookMatch(0, TokenType.WORD) && lookMatch(1, TokenType.EQ)) {
            final String variable = consume(TokenType.WORD).getText();
            consume(TokenType.EQ);
            return new AssignmentStatement(variable, expression());
        }
        if (lookMatch(0, TokenType.WORD) && lookMatch(1, TokenType.LBRACE)) {
            final String variable = consume(TokenType.WORD).getText();
            consume(TokenType.LBRACE);
            Expression index = expression();
            consume(TokenType.RBRACE);
            consume(TokenType.EQ);
            return new ArrayAssigmentStatement(variable, index, expression());
        }
        throw new RuntimeException("Unknown statement");
    }

    private Statement ifElse() {
        final Expression condition = expression();
        final Statement ifStatement = statementOrBlock();
        final Statement elseStatement;
        if (match(TokenType.ELSE)) {
            elseStatement = statementOrBlock();
        } else {
            elseStatement = null;
        }
        return new IfStatement(condition, ifStatement, elseStatement);
    }

    private Statement whileStatement() {
        final Expression condition = expression();
        final Statement statement = statementOrBlock();
        return new WhileStatement(condition, statement);
    }


    private Statement doWhileStatement() {
        final Statement statement = statementOrBlock();
        consume(TokenType.WHILE);
        final Expression condition = expression();
        return new DoWhileStatement(condition, statement);
    }

    private Statement forStatement() {
        final Statement initialization = assignmentStatement();
        consume(TokenType.TWO_DOT);
        final Expression termination = expression();
        consume(TokenType.TWO_DOT);
        final Statement increment = assignmentStatement();
        final Statement statement = statementOrBlock();
        return new ForStatement(initialization, termination, increment, statement);
    }

    private FunctionalMathodStatement functionMathod() {
        final String name = consume(TokenType.WORD).getText();
        consume(TokenType.LPAREN);
        final List<String> argNames = new ArrayList<>();
        while (!match(TokenType.RPAREN)) {
            argNames.add(consume(TokenType.WORD).getText());
            match(TokenType.TWO_DOT);
        }
        final Statement body = statementOrBlock();
        return new FunctionalMathodStatement(name, argNames, body);
    }

    private FunctionalExpression function() {
        final String name = consume(TokenType.WORD).getText();
        consume(TokenType.LPAREN);
        final FunctionalExpression function = new FunctionalExpression(name);
        while (!match(TokenType.RPAREN)) {
            function.addArgument(expression());
            match(TokenType.TWO_DOT);
        }
        return function;
    }

    private Expression expression() {
        return logicalOr();
    }

    private Expression logicalOr() {
        Expression result = logicalAnd();

        while (true) {
            if (match(TokenType.BARBAR)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.OR, result, logicalAnd());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression logicalAnd() {
        Expression result = equality();

        while (true) {
            if (match(TokenType.APMAMP)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.AND, result, equality());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression equality() {
        Expression result = conditional();

        if (match(TokenType.EQEQ)) {
            return new ConditionalExpression(ConditionalExpression.Operator.EQUALS, result, conditional());
        }
        if (match(TokenType.EXCLEQ)) {
            return new ConditionalExpression(ConditionalExpression.Operator.NOT_EQUALS, result, conditional());
        }

        return result;
    }

    private Expression conditional() {
        Expression result = additive();

        while (true) {
            if (match(TokenType.LT)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.LT, result, additive());
                continue;
            }
            if (match(TokenType.LTEQ)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.LTEQ, result, additive());
                continue;
            }
            if (match(TokenType.GT)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.GT, result, additive());
                continue;
            }
            if (match(TokenType.GTEQ)) {
                result = new ConditionalExpression(ConditionalExpression.Operator.GTEQ, result, additive());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression additive() {
        Expression result = multiplicative();

        while (true) {
            if (match(TokenType.PLUS)) {
                result = new BinaryExpression('+', result, multiplicative());
                continue;
            }
            if (match(TokenType.MINUS)) {
                result = new BinaryExpression('-', result, multiplicative());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression multiplicative() {
        Expression result = unary();

        while (true) {
            // 2 * 6 / 3
            if (match(TokenType.STAR)) {
                result = new BinaryExpression('*', result, unary());
                continue;
            }
            if (match(TokenType.SLASH)) {
                result = new BinaryExpression('/', result, unary());
                continue;
            }
            break;
        }

        return result;
    }

    private Expression unary() {
        if (match(TokenType.MINUS)) {
            return new UnaryExpression('-', primary());
        }
        if (match(TokenType.PLUS)) {
            return primary();
        }
        return primary();
    }

    private Expression primary() {
        final Token current = get(0);
        if (match(TokenType.NUMBER)) {
            return new ValueExpression(Double.parseDouble(current.getText()));
        }
        if (match(TokenType.HEX_NUMBER)) {
            return new ValueExpression(Long.parseLong(current.getText(), 16));
        }
        if (get(0).getType() == TokenType.WORD && get(1).getType() == TokenType.LPAREN) {
            return function();
        }
        if (lookMatch(0, TokenType.WORD )&& lookMatch(1, TokenType.LBRACE)) {
            return element();
        }
        if (lookMatch(0, TokenType.LBRACE)) {
            return array();
        }
        if (match(TokenType.WORD)) {
            return new ConstantExpression(current.getText());
        }
        if (match(TokenType.TEXT)) {
            return new ValueExpression(current.getText());
        }
        if (match(TokenType.LPAREN)) {
            Expression result = expression();
            match(TokenType.RPAREN);
            return result;
        }
        throw new RuntimeException("Unknown expression");
    }

    private Expression array() {
        consume(TokenType.LBRACE);
        List<Expression> elements = new ArrayList<>();
        while (!match(TokenType.RBRACE)) {
            elements.add(expression());
            match(TokenType.TWO_DOT);
        }
        return new ArrayExpression(elements);
    }

    private Expression element() {
        final String variable = consume(TokenType.WORD).getText();
        List<Expression> indices = new ArrayList<>();

        do {
            consume(TokenType.LBRACE);
            indices.add(expression());
            consume(TokenType.RBRACE);
        }while (lookMatch(0, TokenType.RBRACE));
        return new ArrayAccessExpression(variable, indices);
    }

    private Token consume(TokenType type) {
        final Token current = get(0);
        if (type != current.getType()) throw new RuntimeException("Token " + current + " doesn't match " + type);
        pos++;
        return current;
    }

    private boolean match(TokenType type) {
        final Token current = get(0);
        if (type != current.getType()) return false;
        pos++;
        return true;
    }

    private Token get(int relativePosition) {
        final int position = pos + relativePosition;
        if (position >= size) return EOF;
        return tokens.get(position);
    }

    private boolean lookMatch(int pos, TokenType type) {
        return get(pos).getType() == type;
    }
}
