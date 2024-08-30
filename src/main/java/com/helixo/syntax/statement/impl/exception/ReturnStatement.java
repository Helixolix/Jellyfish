package com.helixo.syntax.statement.impl.exception;

import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.value.Value;

public class ReturnStatement extends RuntimeException implements Statement {

    private final Expression expression;
    private Value result;

    public ReturnStatement(Expression expression) {
        this.expression = expression;
    }

    public Value getResult() {
        return result;
    }

    @Override
    public void execute() {
        result = expression.eval();
        throw this;
    }

    @Override
    public String toString() {
        return "";
    }
}
