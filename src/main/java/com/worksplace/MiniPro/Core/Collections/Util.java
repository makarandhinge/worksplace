package com.worksplace.MiniPro.Core.Collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Util {
    public static <E> Collection<E> filter(Collection<E> c, Predicate<E> condition){
        return c.stream().filter(condition).collect(Collectors.toList());
    }

    public static <E> void filterInPlace(Collection<E> c, Predicate<E> condition){
            c.removeIf(condition);
    }

}
