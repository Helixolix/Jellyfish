package com.helixo.syntax.statement.impl;

import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.expression.impl.VariabletExpression;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.value.ArrayValue;
import com.helixo.syntax.value.Value;

public class ArrayAssigmentStatement implements Statement {

    private final String variable;
    private final Expression index;
    private final Expression expression;

    public ArrayAssigmentStatement(String variable, Expression index, Expression expression) {
        this.variable = variable;
        this.index = index;
        this.expression = expression;
    }

    @Override
    public void execute() {
        Value var = VariabletExpression.get(variable);
        if (var instanceof ArrayValue) {
            ArrayValue array = (ArrayValue) var;
            array.set((int) index.eval().asNumber(), expression.eval());
        }else {
            throw new RuntimeException("Array expected");
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
