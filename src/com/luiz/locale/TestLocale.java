package com.luiz.locale;

import java.text.NumberFormat;
import java.util.Locale;

public class TestLocale {
    public static void main(String[] args) {
        Locale padrao = Locale.getDefault();
        System.out.println(padrao);
        Locale brasil = Locale.of("pt", "BR");
        int price = 100_000;
        System.out.println(NumberFormat.getCurrencyInstance(brasil).format(price));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(price));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.UK).format(price));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.ITALY).format(price));
    }   
}