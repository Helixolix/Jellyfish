package com.helixo.syntax.statement.impl;

import com.helixo.libjfl.Module;
import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.value.Value;

import java.io.StringReader;
import java.util.Collection;
import java.util.Map;

public final class TakeStatement implements Statement {
    private static final String PACKAGE = "com.helixo.libjfl.";
    public final Expression expression;

    public TakeStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void execute() {
     try {
      String moduleName = expression.eval().asString();
         Module module = (Module) Class.forName(PACKAGE + moduleName).newInstance();
         module.init();
     } catch (Exception e) {}
    }
}
