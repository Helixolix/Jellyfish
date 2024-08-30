package com.helixo;

import com.helixo.test.TestMain;

public class Main {
    public static void main(String[] args) {
        try {
            TestMain.main(args);
        } catch (Exception e) {
            System.out.println(TestMain.ANSI_RED + "Ошибка при выполнении программы: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
