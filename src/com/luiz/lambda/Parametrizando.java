package com.luiz.lambda;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class Parametrizando {
    public static void main(String[] args) {
        record Carro(String marca, String cor, int ano) { }
        var carro1 = new Carro("Audi", "preto", 2018);
        var carro2 = new Carro("Mercedes", "verde", 2022);
        var carro3 = new Carro("Porshe", "branco", 2021);
        var carro4 = new Carro("BMW", "preto", 2023);
        List<Carro> carros = new ArrayList<>(List.of(carro1, carro2, carro3, carro4));

        // List<Carro> filtrada = filtro(carros, new Predicate<Carro>() {
        //     @Override
        //     public boolean test(Carro car) {
        //         return car.marca.equals("Mercedes");
        //     }
        // });

        // Predicate
        List<Carro> filtrada = filtroPredicate(carros, carro -> carro.marca().equals("Mercedes"));
        System.out.println(filtrada);

        // Consumer
        forEach(filtrada, elem -> System.out.printf("%s %s %d%n", elem.marca(), elem.cor(), elem.ano()));

        // Function
        // List<String> coresCarro = map(l1, carro -> carro.cor());
        List<String> coresCarro = map(carros, Carro::cor); // Method reference
        forEach(coresCarro, System.out::println);
    }

    public static <T> List<T> filtroPredicate(List<T> listaBruta, Predicate<T> predicate) {
        List<T> filtrada = new ArrayList<>();
        for (T c : listaBruta) {
            if (predicate.test(c)) {
                filtrada.add(c);
            }
        }
        return filtrada;
    }

    public static <T> void forEach(List<T> listaBruta, Consumer<T> consumer) {
        for (T c : listaBruta) {
            consumer.accept(c);
        }
    }

    public static <T, N> List<N> map(List<T> listaBruta, Function<T, N> function) {
        // N = novo tipo pós função, que pode ser o mesmo do anterior
        List<N> alterada = new ArrayList<>();
        for (T elem : listaBruta) {
            N n = function.apply(elem);
            alterada.add(n);
        }
        return alterada;
    }
}