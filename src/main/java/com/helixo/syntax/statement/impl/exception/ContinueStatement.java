package com.helixo.syntax.statement.impl.exception;

import com.helixo.syntax.statement.Statement;

public class ContinueStatement extends RuntimeException implements Statement {
    @Override
    public void execute() {
        throw this;
    }

    @Override
    public String toString() {
        return "";
    }
}
