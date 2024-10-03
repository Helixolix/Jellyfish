package com.helixo.syntax.statement.impl;

import com.helixo.func.Functions;
import com.helixo.mathod.UserMethodFunction;
import com.helixo.syntax.statement.Statement;

import java.util.List;

public class FunctionalMathodStatement implements Statement {

    private final String name;
    private final List<String> argNames;
    private final Statement body;

    public FunctionalMathodStatement(String name, List<String> argNames, Statement body) {
        this.name = name;
        this.argNames = argNames;
        this.body = body;
    }

    @Override
    public void execute() {
        Functions.set(name, new UserMethodFunction(argNames, body));
    }

    @Override
    public String toString() {
        return "";
    }
}
