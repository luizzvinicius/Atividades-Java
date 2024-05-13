package com.luiz.threads;

import java.util.ArrayList;
import java.util.List;

public class SumMatiz {
    public static void main(String[] args) {
        List<List<Integer>> matriz = new ArrayList<>(
            List.of(
                    List.of(1, 2, 3, 4, 5),
                    List.of(20, 7, 8, 9, 10),
                    List.of(15, 7, 17, 7, 19),
                    List.of(6, 54, 8, 9, 10),
                    List.of(12, 7, 8, 25, 11)
        ));

        for (int i = 0; i < matriz.size(); i++) {
            new SumMatiz().new SomaThread(i + 1 + " Thread", matriz.get(i));
        }
    }

    class SomaThread implements Runnable {
        private String nome;
        private List<Integer> array;
        private int soma;

        public SomaThread(String nome, List<Integer> array) {
            this.array = array;
            this.nome = nome;
            new Thread(this, nome).start();
        }

        @Override
        public void run() {
            this.soma = array.stream().reduce(0, Integer::sum);
            System.out.println("Linha: " + this.nome + " " + this.soma);
        }
    }
}