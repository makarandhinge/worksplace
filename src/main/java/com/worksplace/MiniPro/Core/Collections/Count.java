package com.worksplace.MiniPro.Core.Collections;

import java.util.Collection;

public class Count {

    public static <E> int countMatches(Collection<E> c, E value ){
        int count = 0;
        for(E e : c){
            if(e == value){
                count++;
            }
        }

        return count;
    }
}
