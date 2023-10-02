package com.luiz.streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Streams {
    public static void main(String[] args) {
        record Hq(String title, double price) {}
        List<Hq> revistas = new ArrayList<>(
                List.of(new Hq("Batman", 5), new Hq("Superman", 5.54), new Hq("Iron man", 6.4)));

        /*
         * Pipeline de dados com loops implícitos
         * Operações intermediárias: que devolvem um stream e ainda pode aplicar métodos
         * distinct(): não processar elementos repetidos (utiliza equals e hashCode)
         * sorted
         * skip(n): pula uma quantidade de elementos
         * limit(n): quantos elementos para processar
         * filter
         * map
         * flatMap: usado com listas aninhadas
         * 
         * Operações finais: retornam algo sem ser um stream e o stream fecha.
         * count
         * min e max
         * all, any e noneMatch
         * findFirst, findAny
         * reduce:
         * collect(Collectors.)
         * Collectors:
         * toList, toSet
         * grupingBy: gera um mapa conforme a regra passada ex.: agrupar clientes por endereço
         * joining: apenas com strings
         * 
         * Uso: operações pequenas
         * Debug: usar lambda em bloco; usar o método peek (apenas mostrar o que está acontecendo)
        */

        /*
         * Fazer um exemplo que use vários métodos
         * Ordenar um Map
         */
    }
}