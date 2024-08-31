package com.helixo.test;

import com.helixo.syntax.lexer.Lexer;
import com.helixo.syntax.lexer.Parser;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.tokens.Token;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TestMain {

    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        try {

            String resourcePath = "/test.jfl";
            InputStream inputStream = TestMain.class.getResourceAsStream(resourcePath);

            if (inputStream == null) {
                System.out.println(ANSI_RED + "Ошибка: Ресурс не найден: " + resourcePath);
                return;
            }

            String input = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);


            List<Token> tokens = new Lexer(input).tokenize();


            Statement program = new Parser(tokens).parse();
            program.execute();

        } catch (IOException e) {
            System.out.println(ANSI_RED + "Ошибка при чтении ресурса: " + e.getMessage());
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Ошибка при выполнении программы: " + e.getMessage());
        }
    }
}
