package com.helixo.test;

import com.helixo.syntax.lexer.Lexer;
import com.helixo.syntax.lexer.Parser;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.tokens.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class TestMain {

    public static final String ANSI_RED = "\u001B[31m";

    public static Scanner scanner1;

    public static void main(String[] args) {
        String input;

        while (true) {
            scanner1 = new Scanner(System.in);
            String path = scanner1.nextLine();

            if ("stop".equals(path)){
                break;
            }

            if (args.length > 0) {
                try {
                    input = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    System.out.println(ANSI_RED + "Ошибка при чтении файла: " + e.getMessage());
                    return;
                }
            } else {
                System.out.println(ANSI_RED + "Ошибка: Не указан путь к .jfl файлу.");
                return;
            }

            try {
                List<Token> tokens = new Lexer(input).tokenize();
                Statement program = new Parser(tokens).parse();
                program.execute();
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Ошибка при выполнении программы: " + e.getMessage());
            }
        }
    }
}
