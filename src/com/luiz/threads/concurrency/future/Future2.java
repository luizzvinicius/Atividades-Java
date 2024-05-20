package com.luiz.threads.concurrency.future;

import java.util.Random;
import java.util.concurrent.*;

public class Future2 {
    public static void main(String[] args) {
        // Utiliza umma forma diferente de criar threads
        System.out.println("Solicitando preço das lojas...");
        CompletableFuture<Integer> result = geraPreco();
        CompletableFuture<Integer> result2 = geraPreco();
        CompletableFuture<Integer> result3 = geraPreco();
        CompletableFuture<Integer> result4 = geraPreco();
        // possui o get e join (não trata exceções)
        
        System.out.println(result.join());
        System.out.println(result2.join());
        System.out.println(result3.join());
        System.out.println(result4.join());   
    }

    static CompletableFuture<Integer> geraPreco() {
        return CompletableFuture.supplyAsync(() -> {
            var valor = new Random().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return valor;
        });
    }
}