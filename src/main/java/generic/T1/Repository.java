package generic.T1;


import java.util.HashMap;

public class Repository <T extends Identifiable>{
    private HashMap<Integer, T> items;

    public Repository(){
        this.items = new HashMap<>();
    }

    public void save(T item){
        items.put(item.getId(),item);
    }

    public T findById(int id){
        return items.get(id);
    }

    public boolean deleteById(int id){
        return items.remove(id) != null;

    }

    public int size(){
        return items.size();
    }



}
