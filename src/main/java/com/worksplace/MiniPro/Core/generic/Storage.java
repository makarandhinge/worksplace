package com.worksplace.MiniPro.Core.generic;

import java.util.ArrayList;

public class Storage<T>{
    private ArrayList<T> item;

    public Storage(){
        item = new ArrayList<>();
    }

    void add(T item){
        this.item.add(item);
    }

    T get (int index){
        return this.item.get(index);
    }

    int size(){
        return this.item.size();
    }
}
