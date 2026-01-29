package com.worksplace.MiniPro.Core.Collections.Hashset;

import java.util.HashSet;

public class HashSetExercise2 {
    public static void main(String[] args) {
        HashSet<Integer> numbers = new HashSet<>();

        numbers.add(10);
        numbers.add(20);

        if (!numbers.contains(30)) {
            numbers.add(30);
        }

        System.out.println(numbers);
    }
}
