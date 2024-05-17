package com.luiz.threads.ex_emails;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Emails {
    public static class Membros {
        private Queue<String> emails = new ArrayBlockingQueue<>(20);
        private ReentrantLock l = new ReentrantLock();
        private Condition condicao = l.newCondition();
        private boolean aberto;

        public Membros() {
            this.aberto = true;
        }

        public boolean isAberto() {
            return aberto;
        }

        private int emailsNaFila() {
            l.lock();
            try {
                return this.emails.size();
            } finally {
                l.unlock();
            }
        }

        public void addEmail(String email) {
            l.lock();
            try {
                this.emails.add(email);
                System.out.printf("%s adicionou email%n", Thread.currentThread().getName());
                condicao.signal(); // Dizer que há emails pra enviar
            } finally {
                l.unlock();
            }
        }

        public synchronized String getEmail() {
            return this.emails.peek();
        }

        public void sendEmail() throws InterruptedException {
            System.out.printf("%s verificando se há emails%n", Thread.currentThread().getName());
            l.lock();
            try {
                while (this.emailsNaFila() == 0) {
                    if (!this.aberto) {
                        break;
                    }
                    System.out.printf("%s Sem emails para enviar%n", Thread.currentThread().getName());
                    condicao.await();
                }
                this.emails.poll();
            } finally {
                l.unlock();
            }
        }

        public void parar() {
            l.lock();
            try {
                this.aberto = false;
                condicao.signalAll();
            } finally {
                l.unlock();
            }
        }
    }

    public static void main(String[] args) {
        var scan = new Scanner(System.in);
        var mem = new Membros();

        Runnable r = () -> {
            while (mem.isAberto()) {
                try {
                    String email = mem.getEmail();
                    if (email == null)
                        continue;
                    mem.sendEmail();
                    TimeUnit.SECONDS.sleep(2);
                    System.out.printf("%n%s enviando email%n", Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r, "t1").start();
        new Thread(r, "t2").start();

        while (true) {
            System.out.print("Digite um email (0 para parar): ");
            String email = scan.next();
            if (email.equals("0")) {
                mem.parar();
                break;
            }
            mem.addEmail(email);
        }
        System.out.println("Caixa de emails finalizada");
        scan.close();
    }
}