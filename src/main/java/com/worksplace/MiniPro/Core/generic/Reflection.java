package com.worksplace.MiniPro.Core.generic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Dog> c = Dog.class;
        Class<? super Dog> sc = c.getSuperclass();
        System.out.println(sc);
        System.out.println();
        Class<Color> c1 = Color.class;
        Color[] values = c1.getEnumConstants();
        System.out.println(Arrays.toString(values));
        Constructor<String> cons = String.class.getConstructor();
        String s = cons.newInstance();

        List<String> list = new ArrayList<>();
        Class<?> c3 = list.getClass();
        System.out.println(c3);

    }
}
