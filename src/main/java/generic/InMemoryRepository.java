package generic;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T> implements Repository<T> {

    private List<T> store = new ArrayList<>();

    @Override
    public T findById(long id) {
        return store.get((int) id);
    }

    @Override
    public void save(T entity) {
        store.add(entity);
    }
}
