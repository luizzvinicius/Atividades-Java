package com.luiz.arquivo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TesteArquivo {
    public static void main(String[] args) {
        // java.nio é mais moderno que o java.io
        var path = Paths.get("src/com/luiz/arquivo/teste.txt");
        try {
            var file = Files.readString(path).replace("\r\n", "").replace("    ", "").split(";");
            
            for (String string : file) {
                System.out.println(string);
            }
            
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }
}