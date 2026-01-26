package com.worksplace.MiniPro.Core.generic.L4;

import java.util.Collection;
import java.util.HashMap;

public class Repository<T extends Identifiable<ID>, ID> {
    private HashMap<ID, T> items;

    public Repository(){
        items = new HashMap<>();
    }

    public void save(T item) {
    items.put(item.getId(),item);
    }

    public T findById(ID id){
        return items.get(id);
    }

    public boolean deleteById(ID id){
        return items.remove(id) != null;
    }

    public int size(){
        return items.size();
    }

//    public HashMap<ID,T> getAllitems(){ // Bad framework desgin
//        return items;
//    }

    public Collection<T> findAll(){
        return items.values();
    }
}
