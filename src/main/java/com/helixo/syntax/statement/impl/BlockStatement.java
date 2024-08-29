package com.helixo.syntax.statement.impl;

import com.helixo.syntax.statement.Statement;

import java.util.ArrayList;
import java.util.List;

public class BlockStatement implements Statement {

    private List<Statement> statements;

    public BlockStatement() {
        this.statements = new ArrayList<>();
    }

    public void add(Statement statement) {
        statements.add(statement);
    }

    @Override
    public void execute() {
      for (Statement statement : statements) {
          statement.execute();
      }
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        for (Statement statement : statements) {
            result.append(statement.toString()).append(System.lineSeparator());
        }
        return result.toString();
    }
}
