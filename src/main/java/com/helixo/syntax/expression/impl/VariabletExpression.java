package com.helixo.syntax.expression.impl;

import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.Value;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Data
@AllArgsConstructor
public class VariabletExpression {
    private static Map<String, Value> variables;
    private static final NumberValue ZERO = new NumberValue(0);
    private static final Stack<Map<String, Value>> stack;

    static {
        stack = new Stack<>();
        variables = new HashMap<>();
        variables.put("PI", new NumberValue(Math.PI));
        variables.put("E",  new NumberValue(Math.E));
    }

    public static void push() {
        stack.push(new HashMap<>(variables));
    }

    public static void pop() {
      variables = stack.pop();
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
