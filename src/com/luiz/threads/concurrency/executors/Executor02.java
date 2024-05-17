package com.luiz.threads.concurrency.executors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class Executor02 {
    public static void main(String[] args) throws InterruptedException {
        // Execução de tarefas programadas
        ScheduledExecutorService schedule = null;
        ExecutorService schedule2 = null;

        try {
            schedule = Executors.newScheduledThreadPool(1);
            schedule2 = Executors.newFixedThreadPool(1);
            beep(schedule, schedule2);
            schedule.shutdown();
            schedule.awaitTermination(5, TimeUnit.SECONDS); // tempo máximo de execução após shutdown terminar
        } catch (Exception e) {
            System.out.println("Houve um erro nos executores");
            e.printStackTrace();
        } finally {
            if (schedule != null && schedule2 != null) {
                schedule.shutdownNow();
                schedule2.shutdownNow();
            }
        }
        // invokeAll(List): executar as tarefas no mesmo momento
        // invokeAny: passa uma lista de tarefas, mas retorna apenas a que finalizou primeiro
    }

    private static void beep(final ScheduledExecutorService schedule, ExecutorService schedule2) {
        Runnable r = () -> {
            System.out.print(LocalDateTime.now().format(DateTimeFormatter.ofPattern("H:m:s")));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" Beep");
        };
        // Tempo de execução entre tarefas é fixo (delay)
        // FixedRate executa a tarefa a cada x tempo (tempo informado)
        // * Se a tarefa demorar mais que o tempo passado, o tempo da tarefa que dita
        ScheduledFuture<?> beep = schedule.scheduleWithFixedDelay(r, 0, 2, TimeUnit.SECONDS);
        // ScheduledFuture<?> beep2 = schedule.scheduleAtFixedRate(r, 0, 2, TimeUnit.SECONDS);

        schedule.schedule(() -> {
            System.out.println("Encerrando o Beep");
            beep.cancel(false);
            // beep2.cancel(false);
            schedule.shutdown();
        }, 8, TimeUnit.SECONDS);


        Callable<String> c = () -> {
            System.out.print(LocalDateTime.now().format(DateTimeFormatter.ofPattern("H:m:s")));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            schedule.shutdown();
            return " Beep";
        };
        Future<String> call = schedule.submit(c);
        try {
            var result = call.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}