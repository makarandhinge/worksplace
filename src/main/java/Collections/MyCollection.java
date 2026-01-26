package Collections;

public interface MyCollection<T> {
    void add(T item);
    boolean remove(T item);
    boolean contains(T item);
    int size();
}
