package com.worksplace.MiniPro.Core.Collections;

import java.util.HashMap;

public class MyHashSet<T> implements MyCollection<T>{
    private HashMap<T, Object> map;
    private static final Object PRESENT = new Object();

    public MyHashSet(){
        this.map = new HashMap<>();
    }

    @Override
    public void add(T item) {
        map.put(item,PRESENT);
    }

    @Override
    public boolean remove(T item) {
        return map.remove(item) != null;
    }

    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    @Override
    public int size() {
        return map.size();
    }
}
