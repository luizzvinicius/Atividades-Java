package com.luiz.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Streams {
    public static void main(String[] args) {
        record Hq(String title, double price) {}
        record Leitor(String nome, int idade) {}
        List<Hq> quadrinhos = new ArrayList<>(
                List.of(
                        new Hq("Batman", 5), new Hq("Wonder woman", 5.54),
                        new Hq("Iron man", 6.4), new Hq("Aquaman", 7.54),
                        new Hq("Superman", 5.54), new Hq("Flash", 9))
        );
        
        List<Leitor> leitores = new ArrayList<>(
                List.of(
                        new Leitor("Lucas", 10), new Leitor("Ana", 27),
                        new Leitor("Pedro", 13), new Leitor("Ingridy", 19),
                        new Leitor("Yasmin", 24), new Leitor("Mauricio", 40))
        );
        
        // Ordenar um Map
        Map<Leitor, Hq> quadrinhoFavorito = new HashMap<>();
        for (int i = 0; i < quadrinhos.size(); i++) {
            quadrinhoFavorito.put(leitores.get(i), quadrinhos.get(i));
        }

        Map<Leitor, Hq> mapOrdenado = quadrinhoFavorito.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.comparing(Hq::price).reversed()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        
        mapOrdenado.forEach((q1, q2) -> System.out.println(q1 + " " + q2));
        
        // agrupar
        var leitoresMais18 = mapOrdenado.entrySet().stream()
        .collect(Collectors.groupingBy(e -> e.getKey().idade > 18)); 
        leitoresMais18.forEach((q1, q2) -> System.out.println(q1 + " " + q2));
    }
}