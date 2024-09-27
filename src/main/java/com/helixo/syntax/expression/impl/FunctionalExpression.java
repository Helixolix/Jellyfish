package com.helixo.syntax.expression.impl;

import com.helixo.func.Function;
import com.helixo.func.Functions;
import com.helixo.func.mathod.UserMethodFunction;
import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.value.Value;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FunctionalExpression implements Expression {

    private final String name;
    private final List<Expression> arguments;

    public FunctionalExpression(String name) {
        this.name = name;
        arguments = new ArrayList<>();
    }

    public FunctionalExpression(String name, List<Expression> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public void addArgument(Expression arg) {
        arguments.add(arg);
    }

    @Override
    public Value eval() {
        final int size = arguments.size();
        final Value[] values = new Value[size];
        for (int i = 0; i < size; i++) {
            values[i] = arguments.get(i).eval();
        }

        final Function function = Functions.get(name);
        if (function instanceof UserMethodFunction) {
            final UserMethodFunction userFunction = (UserMethodFunction) function;
            if (size != userFunction.getArgsCount()) throw new RuntimeException("Args count mismatch");

            VariabletExpression.push();
            for (int i = 0; i < size; i++) {
                VariabletExpression.set(userFunction.getArgsName(i), values[i]);
            }
            final Value result = userFunction.execute(values);
            VariabletExpression.pop();
            return result;
        }
        return function.execute(values);
    }

    @Override
    public String toString() {
        return name + "(" + arguments.toString() + ")";
    }
}
