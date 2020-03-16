package Model.ADTs;

import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.Hashtable;

public interface MyIFileTable<S, B> {
    boolean isDefined(S name);

    void add(S name, B fd);

    B get(S v);

    void delete(S v);

    Hashtable<S,B> getAll();
}
