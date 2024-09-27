package com.helixo.func.mathod;

import com.helixo.func.Function;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.statement.impl.exception.ReturnStatement;
import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.Value;

import java.util.List;

public class UserMethodFunction implements Function {

    private final List<String> argNames;
    private final Statement body;

    public UserMethodFunction(List<String> argNames, Statement body) {
        this.argNames = argNames;
        this.body = body;
    }

    public int getArgsCount() {
        return argNames.size();
    }

    public String getArgsName(int index) {
        if (index < 0 || index >= getArgsCount()) return "";
        return argNames.get(index);
    }

    @Override
    public Value execute(Value... args) {
        try {
            body.execute();
            return NumberValue.ZERO;
        }catch (ReturnStatement rt) {
            return rt.getResult();
        }
    }
}
