package com.luiz.varargs;

public class TesteVarargs {
    public static void main(String[] args) {
        System.out.println(soma(5, 4));
        System.out.println(soma(1, 2, 2, 2, 2, 2, 2));
    }

    static int soma(int a, int b) {
        return a + b;
    }

    static int soma(int a, Integer... vetor) {
        int soma = 0;
        for (int elem : vetor) {
            soma += elem;
        }

        return a + soma;
    }
}