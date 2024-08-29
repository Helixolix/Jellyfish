package com.helixo.syntax.statement.impl;

import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.statement.impl.exception.BreakStatement;
import com.helixo.syntax.statement.impl.exception.ContinueStatement;

public final class DoWhileStatement implements Statement {

    private final Expression condition;
    private final Statement statement;

    public DoWhileStatement(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public void execute() {
        do {
            try {
                statement.execute();
            } catch (BreakStatement e) {
                break;
            }catch (ContinueStatement e) {
                // continue;
            }
        }
        while (condition.eval().asNumber() != 0);
    }

    @Override
    public String toString() {
        return "";
    }
}
