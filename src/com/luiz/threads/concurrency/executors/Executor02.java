package com.luiz.threads.concurrency.executors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class Executor02 {
    public static void main(String[] args) throws InterruptedException {
        // Execução de tarefas programadas
        final ScheduledExecutorService schedule = Executors.newScheduledThreadPool(1);
        final ExecutorService schedule2 = Executors.newFixedThreadPool(1);

        beep(schedule, schedule2);
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
        // Espera a thread acordar para contar o delay
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