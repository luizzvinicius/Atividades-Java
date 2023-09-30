package com.luiz.optionals;

import java.time.LocalDate;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        var pessoa1 = new Pessoa("Luiz", null, 2005);

        Optional<String> sexo = Optional.ofNullable(pessoa1.getSexo()); // .of é certeza que não é nulo | ofNullable é melhor

        sexo.ifPresentOrElse(
            System.out::println,
            () -> {
               pessoa1.setSexo("sexo não informado");
               System.out.println(pessoa1.getSexo());
            }
        ); // orElse: valor padrão

        Optional<Integer> idade = calculaIdade(pessoa1.getDataNascimento());
        idade.ifPresent(i -> System.out.println("Idade: " + i));
    }

    static Optional<Integer> calculaIdade(int dataNascimento) {
        if (dataNascimento > 0) {
            return Optional.of(LocalDate.now().getYear() - dataNascimento);
        }
        return Optional.empty();
    }
}

class Pessoa {
    private String nome, sexo;
    private int dataNascimento;

    public Pessoa(String nome, String sexo, int dataNascimento) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getDataNascimento() {
        return dataNascimento;
    }
}