package com.helixo.syntax.expression.impl;

import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.Value;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UnaryExpression implements Expression {
    private Expression expr1;
    private char operation;

    @Override
    public Value eval() {
        switch (operation) {
            case '-': return new NumberValue(-expr1.eval().asNumber());
            case '+':
            default:
                return expr1.eval();
        }
    }

    public UnaryExpression(char operation, Expression expr1) {
        this.expr1 = expr1;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return String.format("%c %s", operation, expr1);
    }
}
