package com.luiz.classe_aninhada;

public class Externa {
    private final String texto = "Texto externo";

    public void imprimirTextos() {
        var txt = new Externa().new Interna().texto;
        System.out.println("acessando texto da interna pela externa: " + txt);
    }

    public String getTexto() {
        return this.texto;
    }

    public class Interna {
        private final String texto = "Texto interno";

        public void imprimirTextos() {
            System.out.println(Externa.this.texto);
            System.out.println(this.texto);
        }

        public String getTexto() {
            return this.texto;
        }
    }
}