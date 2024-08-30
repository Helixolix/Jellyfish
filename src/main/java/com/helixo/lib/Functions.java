package com.helixo.lib;

import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.StringValue;
import com.helixo.syntax.value.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Functions {
    private static final Map<String, Function> functions;

    static {
        functions = new HashMap<>();
        functions.put("sin", new Function() {

            @Override
            public Value execute(Value... args) {
                if (args.length != 1) throw new RuntimeException("One arg expected");
                return new NumberValue(Math.sin(args[0].asNumber()));
            }
        });
        functions.put("cos", (Function) (Value... args) -> {
            if (args.length != 1) throw new RuntimeException("One arg expected");
            return new NumberValue(Math.cos(args[0].asNumber()));
        });
        functions.put("echo", args -> {
            for (Value arg : args) {
                System.out.println(arg.asString());
            }
            return NumberValue.ZERO;
        });

        functions.put("IntroduceLine", (Function) (Value[] args) -> {
            try(Scanner scanner = new Scanner(System.in)) {
                return new StringValue(scanner.nextLine());
            }
        });

        functions.put("IntroduceInt", (Function) (Value[] args) -> {
            try(Scanner scanner = new Scanner(System.in)) {
                return new NumberValue(scanner.nextInt());
            }
        });

//        functions.put("length", (Function) (Value[] args) -> {
//            try(Scanner scanner = new Scanner(System.in)) {
//                return new NumberValue(scanner.nextLine().length());
//            }
//        });
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
