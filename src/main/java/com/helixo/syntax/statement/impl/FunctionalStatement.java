package com.helixo.syntax.statement.impl;

import com.helixo.syntax.expression.impl.FunctionalExpression;
import com.helixo.syntax.statement.Statement;

public class FunctionalStatement implements Statement {

    private final FunctionalExpression function;

    public FunctionalStatement(FunctionalExpression function) {
        this.function = function;
    }

    @Override
    public void execute() {
        function.eval();
    }

    @Override
    public String toString() {
        return "";
    }
}
