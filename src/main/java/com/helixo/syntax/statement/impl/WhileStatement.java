package com.helixo.syntax.statement.impl;

import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.statement.impl.exception.BreakStatement;
import com.helixo.syntax.statement.impl.exception.ContinueStatement;

public final class WhileStatement implements Statement {

    private final Expression condition;
    private final Statement statement;

    public WhileStatement(Expression condition, Statement statement) {
        this.condition = condition;
        this.statement = statement;
    }

    @Override
    public void execute() {
        while (condition.eval().asNumber() != 0) {
            try {
               statement.execute();
            } catch (BreakStatement e) {
                break;
            }catch (ContinueStatement e) {
                // continue;
            }
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
