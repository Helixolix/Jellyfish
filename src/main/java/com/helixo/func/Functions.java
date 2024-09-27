package com.helixo.func;

import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.Value;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Functions {
    private static final Map<String, Function> functions;
    private static JFrame window;

    static {
        functions = new HashMap<>();
    }

    public static boolean isExists(String key) {
        return functions.containsKey(key);
    }

    public static Function get(String key) {
        if (!isExists(key)) throw new RuntimeException("Unknown function " + key);
        return functions.get(key);
    }

    public static void set(String key, Function function) {
        functions.put(key, function);
    }
}
