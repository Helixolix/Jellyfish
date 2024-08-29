package com.helixo.syntax.expression.impl;

import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.StringValue;
import com.helixo.syntax.value.Value;

public class BinaryExpression implements Expression {
    private Expression expr1, expr2;
    private char operation;

    public BinaryExpression(char operation, Expression expr1, Expression expr2)  {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operation = operation;
    }

    @Override
    public Value eval() {
        final Value value1 = expr1.eval();
        final Value value2 = expr2.eval();

        final double number1 = expr1.eval().asNumber();
        final double number2 = expr2.eval().asNumber();

        if (value1 instanceof StringValue) {
            final String str1 = value1.asString();

            switch (operation) {
                case '*': {
                    int interations = (int) value2.asNumber();
                    StringBuilder buffer = new StringBuilder();
                    for (int i = 0; i < interations; i++) {
                        buffer.append(str1);
                    }
                    return new StringValue(buffer.toString());
                }
                case '+':
                default:
                    return new StringValue(str1 + value2.asString());
            }
        }

        switch (operation) {
            case '-': return new NumberValue(number1 - number2);
            case '*': return new NumberValue(number1 * number2);
            case '/': return new NumberValue(number1 / number2);
            case '+':
            default:
                return new NumberValue(number1 + number2);
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
