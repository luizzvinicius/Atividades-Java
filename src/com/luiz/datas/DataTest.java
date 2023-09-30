package com.luiz.datas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DataTest {
    public static void main(String[] args) {
        LocalDate dataAtual = LocalDate.now();
        System.out.println("Apenas data: " + dataAtual);
        System.out.printf("Faltam %d dias para o meu aniversário%n", ChronoUnit.DAYS.between(dataAtual, LocalDate.parse("2024-06-15")));

        LocalDateTime dataComHora = LocalDateTime.now();
        var apenasData = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dataComHora.toLocalDate());
        var apenasHoras = DateTimeFormatter.ofPattern("HH:mm:ss").format(dataComHora.toLocalTime());
        System.out.printf("Seu acesso foi no dia %s às %s%n", apenasData, apenasHoras);
        
        String dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(dataComHora);
        var dataEHora = List.of(dataFormatada.split(" "));
        System.out.println("Data: " + dataEHora.get(0));
        System.out.println("Hora: " + dataEHora.get(1));

        System.out.println("Você permaneceu no lugar: " + ChronoUnit.MINUTES.between(dataComHora.toLocalTime(), LocalTime.now()) + " minutos");
    }
}