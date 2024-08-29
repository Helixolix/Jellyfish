package com.helixo.syntax.statement.impl;


import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.statement.impl.exception.BreakStatement;
import com.helixo.syntax.statement.impl.exception.ContinueStatement;

public final class ForStatement implements Statement {

    private final Statement initialization;
    private final Expression termination;
    private final Statement increment;
    private final Statement statement;

    public ForStatement(Statement initialization, Expression termination, Statement increment, Statement block) {
        this.initialization = initialization;
        this.termination = termination;
        this.increment = increment;
        this.statement = block;
    }

    @Override
    public void execute() {
        for (initialization.execute(); termination.eval().asNumber() != 0; increment.execute()) {
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
