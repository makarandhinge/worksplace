package com.worksplace.MiniPro.Core.Collections;

public class MyArrayList<T> implements MyCollection<T> {

    private Object[] data = new Object[10];
    private int size = 0;

    @Override
    public void add(Object item) {
        data[size++] = item;
    }

    @Override
    public boolean remove(Object item) {
        for(int i=0; i<size;i++){
            if(data[i].equals(item)){
                data[i] = data[size - 1];
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object item) {
        for(int i=0; i<size; i++){
            if(data[i].equals(item)) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }
}
