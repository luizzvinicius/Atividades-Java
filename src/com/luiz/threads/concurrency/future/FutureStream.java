package com.luiz.threads.concurrency.future;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FutureStream {
    public static void main(String[] args) {
        List<String> lojas = List.of("Loja 1", "Loja 2", "Loja 3", "Loja 4");

        // Jeito errado (Síncrono)
        List<Integer> precos = lojas.stream()
                .map(FutureStream::geraPreco)
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        System.out.println(precos);

        // Jeito certo (Assíncrono)
        List<CompletableFuture<Integer>> requests = lojas.stream()
                .map(FutureStream::geraPreco)
                .collect(Collectors.toList());
        // Juntar em único stream
        List<Integer> precos2 = requests.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        System.out.println(precos2);

    }

    static CompletableFuture<Integer> geraPreco(String loja) {
        return CompletableFuture.supplyAsync(() -> {
            var valor = new Random().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(loja + ": " + valor);
            return valor;
        });
    }
}