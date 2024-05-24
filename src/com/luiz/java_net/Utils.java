package com.luiz.java_net;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Utils implements AutoCloseable {
    private Scanner scan;

    public Utils() {
        this.scan = new Scanner(System.in);
    }

    public void showArray(String[] opcoes) {
        for (int i = 0; i < opcoes.length; i++) {
            System.out.printf("%d- %s%n", i+1, opcoes[i]);
        }
    }

    public String lerString(String msg, String erroMessage) {
        String palavra;
        while (true) {
            System.out.print(msg);
            palavra = this.scan.nextLine().strip();
            if (!Pattern.matches("^[a-zA-ZÀ-ÿ\s]+$", palavra)) {
                System.out.println(erroMessage + "\n");
                continue;
            }
            return palavra;
        }
    }

    public double lerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            var num = this.scan.nextLine().strip();
            try {
                return Double.parseDouble(num);
            } catch (NumberFormatException e) {
                System.out.printf("Número inválido. %s%n%n", e.getMessage());
            }
        }
    }

    public int lerInt(String msg) {
        while (true) {
            System.out.print(msg);
            var num = this.scan.nextLine().strip();
            try {
                return Integer.parseInt(num);
            } catch (NumberFormatException e) {
                System.out.printf("Número inteiro inválido. %s%n%n", e.getMessage());
            }
        }
    }

    public int lerOption(String msg, int min, int max, String erroMessage) {
        int num;
        while (true) {
            num = this.lerInt(msg);
            try {
                if (num < min || num > max) {
                    throw new IllegalArgumentException(erroMessage + "\n");
                }
                return num-1;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void close() {
        this.scan.close();
    }
}