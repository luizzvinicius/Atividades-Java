package com.luiz.threads.concurrency.future;

import java.util.Random;
import java.util.concurrent.*;

public class Future1 {
    public static void main(String[] args) {
        // ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor = Executors.newFixedThreadPool(2); // Comparar desempenho dos executores

        System.out.println("Solicitando preço das lojas...");

        // Síncrono
        // try {
        //     System.out.println(geraPreco(executor).get());
        //     System.out.println(geraPreco(executor).get());
        //     System.out.println(geraPreco(executor).get());
        //     System.out.println(geraPreco(executor).get());
        // } catch (CancellationException | ExecutionException | InterruptedException e) {
        //     System.out.println("Solicitacão não atendida. Tente novamente.");
        // }

        // Assíncrono
        Future<Integer> result = geraPreco(executor);
        Future<Integer> result2 = geraPreco(executor);
        Future<Integer> result3 = geraPreco(executor);
        Future<Integer> result4 = geraPreco(executor);
        try {
            System.out.println(result.get());
            System.out.println(result2.get());
            System.out.println(result3.get());
            System.out.println(result4.get());
        } catch (CancellationException | ExecutionException | InterruptedException e) {
            System.out.println("Solicitacão não atendida. Tente novamente.");
        }

        executor.shutdown();
    }

    static Future<Integer> geraPreco(ExecutorService executor) {
        return executor.submit(() -> {
            var valor = new Random().nextInt(10);
            TimeUnit.SECONDS.sleep(5);
            return valor;
        });
    }
}