package com.worksplace.MiniPro.Core.Collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class WarmUp {

    public static void main(String[] args) {


        List<String> list = new ArrayList<>();
        list.add("Makarand");
        list.add("Indy");
        list.add("Adarsh");
        list.add("Ritesh");
        list.add("Kuldeep");
        list.add("Indy");
        list.add("Indy");

        Iterator<String> it =  list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println();
        Iterator<String> it2 = list.iterator();
        while(it2.hasNext()){
            if(it2.next().equals("Makarand")){
                it2.remove();
                break;
            }
        }
        System.out.println();
        for(String s : list){
            System.out.println(s);
        }

        System.out.println();
        System.out.println("Using own print class");
        Printer.printCollection(list);

        System.out.println();
        System.out.println(Count.countMatches(list,"Indy"));

        Collection<String> list2 = Util.filter(list, s -> s.startsWith("I"));
        System.out.println(list2);

        System.out.println();
        Util.filterInPlace(list,s -> s.endsWith("h"));
        System.out.println(list);
    }
}
