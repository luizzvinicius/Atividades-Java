package com.luiz.enums;

public class TesteEnum {
    public static void main(String[] args) {
        var domingo = DiaSemana.DOMINGO;
        System.out.println(domingo + "\n");

        DiaSemana[] semana = DiaSemana.values();
        for (DiaSemana dia : semana) {
            System.out.println(dia + " -> " + dia.getValor());
        }
    }   
}