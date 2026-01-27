package com.worksplace.MiniPro.Core.Collections;

import java.util.HashSet;

public class HashSetEx {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(10);
        set.add(20);
        set.add(10);
        set.add(30);

        System.out.println(set.size());

        for(Integer i : set){
            System.out.println(i);
        }

        System.out.println(set.contains(20));
        System.out.println();
        System.out.println("Using own print class");
        Printer.printCollection(set);

        System.out.println();
        System.out.println(Count.countMatches(set,10));
    }
}
