package com.helixo.syntax.expression.impl;

import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.value.Value;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConstantExpression implements Expression {
    private final String name;

    @Override
    public Value eval() {
        if (!VariabletExpression.isExists(name)) throw new RuntimeException("Const does not exists");
        return VariabletExpression.get(name);
    }
}