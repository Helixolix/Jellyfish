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
    public static void main(String[] args) {
        try {
            // загрузка файла .dil из ресов
            String resourcePath = "/test.dil";
            InputStream inputStream = TestMain.class.getResourceAsStream(resourcePath);

            if (inputStream == null) {
                System.out.println("Ошибка: Ресурс не найден: " + resourcePath);
                return;
            }

            String input = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            // токенизация содержимого файла
            List<Token> tokens = new Lexer(input).tokenize();

            // паринг токенов и выполнение программы
            Statement program = new Parser(tokens).parse();
            program.execute();

        } catch (IOException e) {
            System.out.println("Ошибка при чтении ресурса: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении программы: " + e.getMessage());
        }
    }
}
