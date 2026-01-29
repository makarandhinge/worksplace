package com.worksplace.MiniPro.Core.Collections.Hashset;

import java.util.HashSet;

public class HashSetExercise1 {
    public static void main(String[] args) {

        HashSet<String>  names = new HashSet<>();

        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("Alice");

        System.out.println(names);

    }
}
