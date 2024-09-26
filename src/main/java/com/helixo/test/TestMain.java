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

    public static String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static Scanner scanner1;

    public static void main(String[] args) throws InterruptedException {
        String input;

        while (true) {
            System.out.println("Введите путь к jfl файлу:");

            scanner1 = new Scanner(System.in);
            String path = scanner1.nextLine();

            if ("stop".equals(path)){
                break;
            }

            try {
                input = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            } catch (IOException e) {

                throw new RuntimeException(e);
            }


            try {
                List<Token> tokens = new Lexer(input).tokenize();
                Statement program = new Parser(tokens).parse();
                program.execute();
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Ошибка при выполнении программы: " + e.getMessage());
                break;
            }
        }
    }
}
