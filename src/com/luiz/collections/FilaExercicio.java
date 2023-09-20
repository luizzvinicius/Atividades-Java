package com.luiz.collections;

import java.util.Queue;
import java.util.Random;
import java.util.PriorityQueue;
import java.util.ArrayDeque;

public class FilaExercicio {
    public static void main(String[] args) {
        record Senha(int num, int prioridade) {

        }

        Queue<Senha> filaNormal = new ArrayDeque<>();
        Queue<Senha> filaPrioridade = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.num(), s2.num()));
        final int MAX_PRIORIDADE_ATENDIDAS = 3;

        for (var i = 1; i <= 20; i++) {
            int prioridade = new Random().nextInt(2) + 1;
            if (prioridade == 1) {
                filaPrioridade.add(new Senha(i, prioridade));
            } else {
                filaNormal.add(new Senha(i, prioridade));
            }
        }
        
        filaNormal.forEach(s -> System.out.println(s));
        System.out.println();
        filaPrioridade.forEach(s -> System.out.println(s));
        System.out.println();

        int atendidos = 0;
        while (!filaNormal.isEmpty() || !filaPrioridade.isEmpty()) {
            if (filaPrioridade.peek() != null && atendidos < MAX_PRIORIDADE_ATENDIDAS) {
                System.out.println(filaPrioridade.poll());
                atendidos++;
            } else if (filaNormal.peek() != null) {
                atendidos = 0;
                System.out.println("\n"+ filaNormal.poll());
            }
        }
    }
}