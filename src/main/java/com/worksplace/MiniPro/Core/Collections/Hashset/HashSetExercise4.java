package com.worksplace.MiniPro.Core.Collections.Hashset;

import java.util.HashSet;

public class HashSetExercise4 {

    public static void main(String[] args) {
        HashSet<String> colors = new HashSet<>();
        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");

        for (String color : colors) {
            System.out.println(color);
        }
    }
}
