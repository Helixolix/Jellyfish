package com.helixo.lib;

import com.helixo.syntax.statement.impl.PrintLnStatement;
import com.helixo.syntax.value.ArrayValue;
import com.helixo.syntax.value.NumberValue;
import com.helixo.syntax.value.StringValue;
import com.helixo.syntax.value.Value;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private static JFrame window;

    static {
        functions = new HashMap<>();

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

        functions.put("Throw", (Value[] args) -> {
           throw new RuntimeException(args[0].asString());
        });
    }

    private static JButton findButtonByText(String text) {
        for (Component comp : window.getContentPane().getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals(text)) {
                return (JButton) comp;
            }
        }
        return null;
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
