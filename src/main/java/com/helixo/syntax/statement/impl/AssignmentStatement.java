package com.helixo.syntax.statement.impl;


import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.expression.impl.VariabletExpression;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.value.Value;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignmentStatement implements Statement {
    private final String variable;
    private final Expression expression;

    @Override
    public void execute() {
        final Value result = expression.eval();
        VariabletExpression.set(variable, result);
    }

    @Override
    public String toString() {
        return "";
    }
}

