package com.helixo.syntax.statement.impl;


import com.helixo.syntax.expression.Expression;
import com.helixo.syntax.statement.Statement;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IfStatement implements Statement {

    private final Expression expression;
    private final Statement ifStatment, elseStatment;

    @Override
    public void execute() {
      double result = expression.eval().asNumber();
      if (result != 0) {
          ifStatment.execute();
      } else if (elseStatment != null) {
          elseStatment.execute();
      }
    }

    @Override
    public String toString() {
        return "";
    }
}
