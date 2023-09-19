package com.luiz.collections;

import java.util.*;

public class TesteCollection {
    public static void main(String[] args) {
        // var p1 = new Produto("dadsas", 23, 25, 100);
        // var p3 = new Produto("aa", 27, 30, 90);
        // var p2 = new Produto("dadsas", 23, 25, 100);
        
        
        Integer[] l2 = new Integer[] {5,56,6};

        List<Integer> l3 = new ArrayList<>(List.of(l2));
        
        l2[2] = 9;
        l3.add(7);
        System.out.println(Arrays.toString(l2));
        System.out.println(l3);
    }   
}