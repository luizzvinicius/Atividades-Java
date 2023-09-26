package com.luiz.generics;

import java.util.List;

public class WildCard {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3);
        
        printlist(list);
        printlist2(list);

        List<String> l2 = adicionaElemento(new String("Luiz"));
        printlist2(l2);
    }

    public static void printlist(List<?> lista) {
        System.out.println(lista);
    }
    // Preferível generics a wildcard
    public static <T> void printlist2(List<T> lista) {
        System.out.println(lista);
    }

    public static <T> List<T> adicionaElemento(T elemento) {
        List<T> lista = List.of(elemento);
        return lista;
    }
}