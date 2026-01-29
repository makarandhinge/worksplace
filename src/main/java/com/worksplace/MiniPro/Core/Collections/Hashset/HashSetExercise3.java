package com.worksplace.MiniPro.Core.Collections.Hashset;

import java.util.HashSet;

public class HashSetExercise3 {
    public static void main(String[] args) {
        HashSet<String> fruits = new HashSet<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        fruits.remove("Banana");
        System.out.println(fruits);
    }
}
