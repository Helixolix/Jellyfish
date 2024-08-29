package com.helixo.syntax.expression.impl;

import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.value.Value;

public class ConstantExpression implements Expression {
    private final String name;

    public ConstantExpression(String name) {
        this.name = name;
    }

    @Override
    public Value eval() {
        if (!VariabletExpression.isExists(name)) throw new RuntimeException("Const does not exists");
        return VariabletExpression.get(name);
    }

    @Override
    public String toString() {
        return "";
    }
}