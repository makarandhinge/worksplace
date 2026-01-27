package com.worksplace.MiniPro.Core.Collections;

import java.util.Collection;
import java.util.Iterator;

public class Printer {
    public static <E> void printCollection(Collection<E> c){
        Iterator<E> iterator = c.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
