package Model.ADTs;

import Model.Values.Value;

import java.util.Hashtable;
import java.util.Map;

public interface MyIDictionary<T, V> {
    boolean hasKey(T id);

    V lookup(T id);

    void update(T id, V val);

    void add(T id, V val);

    MyIDictionary<T, V> clone();

    Map<T, V> getContent();

    Hashtable<T,V> getAll();
}
