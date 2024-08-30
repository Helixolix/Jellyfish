package com.helixo.lib;

import com.helixo.syntax.statement.impl.PrintLnStatement;
import com.helixo.syntax.value.ArrayValue;
import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.StringValue;
import com.helixo.syntax.value.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Functions {
    private static final Map<String, Function> functions;

    static {
        functions = new HashMap<>();
        functions.put("sin", args -> {
            if (args.length != 1) throw new RuntimeException("One arg expected");
            return new NumberValue(Math.sin(args[0].asNumber()));
        });

        functions.put("cos", (Value... args) -> {
            if (args.length != 1) throw new RuntimeException("One arg expected");
            return new NumberValue(Math.cos(args[0].asNumber()));
        });
        functions.put("echo", args -> {
            for (Value arg : args) {
                System.out.println(arg.asString());
            }
            return NumberValue.ZERO;
        });

        functions.put("IntroduceLine", (Value[] args) -> {
            try(Scanner scanner = new Scanner(System.in)) {
                return new StringValue(scanner.nextLine());
            }
        });

        functions.put("IntroduceInt", (Value[] args) -> {
            try(Scanner scanner = new Scanner(System.in)) {
                return new NumberValue(scanner.nextInt());
            }
        });

        functions.put("many", new Function() {
            @Override
            public Value execute(Value... args) {
             return createArray(args, 0);
            }

            private ArrayValue createArray(Value[] args, int index) {
                int size = (int) args[index].asNumber();
                int last = args.length;
                ArrayValue array = new ArrayValue(size);
                if (index == last) {
                    for (int i = 0; i < size; i++) {
                        array.set(i, NumberValue.ZERO);
                    }
                } else if (index < last) {
                    for (int i = 0; i < size; i++) {
                        array.set(i, createArray(args, index + 1));
                    }
                }
                return array;
            }
        });

        functions.put("random", (Value[] args) -> {
            Random random = new Random();
            return new NumberValue(random.nextInt((int) args[0].asNumber(), (int) args[1].asNumber()));
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
