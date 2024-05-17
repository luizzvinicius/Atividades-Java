package com.luiz.threads.ex_emails;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Emails {

    public static class Membros {
        private Queue<String> emails = new ArrayBlockingQueue<>(20, true);
        private boolean aberto;

        public Membros() {
            this.aberto = true;
        }

        public boolean isAberto() {
            return aberto;
        }

        private synchronized int emailsNaFila() {
            synchronized (this.emails) {
                return this.emails.size();
            }
        }

        public synchronized void addEmail(String email) {
            synchronized (this.emails) {
                System.out.printf("%s adicionou email%n", Thread.currentThread().getName());
                this.emails.add(email);
                notifyAll();
            }
        }

        public String getEmail() {
            synchronized (this.emails) {
                return this.emails.peek();
            }
        }

        public void sendEmail() {
            synchronized (this.emails) {
                while (this.emailsNaFila() == 0) {
                    if (!this.aberto) {
                        break;
                    }
                    System.out.printf("%n%s Sem email%n", Thread.currentThread().getName());
                    try {
                        this.emails.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.emails.poll();
            }
        }

        public synchronized void parar() {
            this.aberto = false;
            notifyAll();   
        }
    }

    public static void main(String[] args) {
        var scan = new Scanner(System.in);
        var mem = new Membros();

        Runnable r = () -> {
            while (mem.isAberto()) {
                try {
                    String email = mem.getEmail();
                    if (email == null) {
                        continue;
                    }
                    mem.sendEmail();
                    TimeUnit.SECONDS.sleep(2);
                    System.out.printf("%n%s enviando email%n", Thread.currentThread().getName());
                } catch (InterruptedException e) {
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
    }
}