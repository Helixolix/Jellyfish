package com.helixo.lib;

import com.helixo.syntax.statement.impl.PrintLnStatement;
import com.helixo.syntax.value.ArrayValue;
import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.StringValue;
import com.helixo.syntax.value.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        functions.put("Introduce_Line", (Value[] args) -> {
            try(Scanner scanner = new Scanner(System.in)) {
                String a = scanner.nextLine();
                return new StringValue(a);
            }
        });

        functions.put("Introduce_Int", (Value[] args) -> {
            Scanner scanner = new Scanner(System.in);
                int a = scanner.nextInt();
                return new NumberValue(a);
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

        functions.put("Length", (Value[] args) -> {
            int a = args[0].asString().length();
            return new NumberValue(a);
        });

        functions.put("sleep", (Value[] args) -> {
            try {
                Thread.sleep((long) args[0].asNumber());
                return NumberValue.ZERO;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        functions.put("Write_File", (Value[] args) -> {
            StringValue path = (StringValue) args[0];
            StringValue content = (StringValue) args[1];
            try {
                Files.write(Paths.get(path.asString()), content.asString().getBytes(StandardCharsets.UTF_8));
                return NumberValue.ZERO;
            } catch (IOException e) {
                throw new RuntimeException("Error writing file: " + e.getMessage());
            }
        });

        functions.put("Read_File", (Value[] args) -> {
            StringValue path = (StringValue) args[0];
            try {
                String content = Files.readString(Paths.get(path.asString()));
                return new StringValue(content);
            } catch (IOException e) {
                throw new RuntimeException("Error reading file: " + e.getMessage());
            }
        });

        functions.put("new_Throw", (Value[] args) -> {
           throw new RuntimeException(args[0].asString());
        });

        functions.put("create_New_File", (Value[] args) -> {
            StringValue path = (StringValue) args[0];
            try {
                Files.createFile(Paths.get(path.asString()));
                return NumberValue.ZERO;
            } catch (IOException e) {
                throw new RuntimeException("Error creating file: " + e.getMessage());
            }
        });
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
