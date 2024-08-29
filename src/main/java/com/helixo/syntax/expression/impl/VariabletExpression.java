package com.helixo.syntax.expression.impl;

import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.Value;

import java.util.HashMap;
import java.util.Map;

public class VariabletExpression {
    private static Map<String, Value> variables;
    private static final NumberValue ZERO = new NumberValue(0);

    static {
        variables = new HashMap<>();
        variables.put("PI", new NumberValue(Math.PI));
        variables.put("E",  new NumberValue(Math.E));
    }

    public static boolean isExists(String key) {
        return variables.containsKey(key);
    }

    public static Value get(String key) {
        if (!isExists(key)) return ZERO;
        return variables.get(key);
    }

    public static void set(String key, Value value){
        variables.put(key, value);
    }
}
