package com.luiz.threads.concurrency;

import java.util.*;
import java.util.concurrent.*;

public class SafeCollections {
    public static void main(String[] args) {
        // O ideal é estar trabalhando com objetos finais

        // cria nova lista caso um elemento tenha sido adicionado ou removido
        // Não é performático para escrita
        CopyOnWriteArrayList<Integer> l1 = new CopyOnWriteArrayList<>(); // set também

        // Não deixa inserir mais que a capacidade inicial. Caso exceda, a thread é bloqueada
        BlockingQueue<Integer> l2 = new ArrayBlockingQueue<>(5);

        // ConcurrentLinkedQueue, SynchronousQueue, LinkedBlockingQueue
        // Tem funcionalidade das três
        BlockingQueue<Integer> l3 = new LinkedTransferQueue<>();
        // Deque: double ended queue, pode mexer tanto no fim como no começo da fila
        // Sobrescrever a lista. Tá implementando o syncronized por baixo dos panos de todos os métodos
        List<Integer> l4 = new ArrayList<>(); // Collections.synchronizedList(new ArrayList<>());
        l4 = Collections.synchronizedList(l4);
    }
    // volatile: fala pro programa consultar a variável apenas da RAM, não do cache do processador
    // usar volatile ainda gera condições de corrida. Sendo o ideal sincronizar o código ou usar atomic...
}