package com.helixo.syntax.statement.impl;


import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.statement.Statement;

public class PrintLnStatement implements Statement {

    private Expression expression;

    public PrintLnStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
        System.out.println(expression.eval());
    }

    @Override
    public String toString() {
        return String.format("",expression);
    }
}
