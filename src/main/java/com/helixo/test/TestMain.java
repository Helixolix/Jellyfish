package com.helixo.test;

import com.helixo.syntax.lexer.Lexer;
import com.helixo.syntax.lexer.Parser;
import com.helixo.syntax.statement.Statement;
import com.helixo.syntax.tokens.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        String path = scanner.nextLine();

        final String input = new String( Files.readAllBytes(Paths.get(path)), "UTF-8");

        List<Token> token = new Lexer(input).tokenize();

//        for (Token token : tokens) {
//            System.out.println(token);
//        }

        Statement programm = new Parser(token).parse();
        System.out.println(programm.toString());
        programm.execute();
    }
}
