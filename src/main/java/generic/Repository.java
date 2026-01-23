package generic;

public interface Repository<T> {
    T findById(long id);
    void save(T entity);
}
