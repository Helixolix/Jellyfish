package com.helixo.syntax.expression.impl;

import com.helixo.lib.Function;
import com.helixo.lib.Functions;
import com.helixo.lib.UserMethodFunction;
import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.value.ArrayValue;
import com.helixo.syntax.value.Value;

import java.util.ArrayList;
import java.util.List;

public class ArrayExpression implements Expression {

    private final List<Expression> arguments;

    public ArrayExpression(List<Expression> arguments) {
        this.arguments = arguments;
    }

    @Override
    public Value eval() {
       int size = arguments.size();
        ArrayValue array = new ArrayValue(size);
        for (int i = 0; i < size; i++) {
            array.set(i, arguments.get(i).eval());
        }
        return array;
    }

    @Override
    public String toString() {
        return "";
    }
}
