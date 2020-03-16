package Model.ADTs;

import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.Hashtable;

public class MyFileTable<S, B> implements MyIFileTable<S, B> {
    private Hashtable<S, B> filetable;
    public MyFileTable()
    {
        filetable = new Hashtable<S,B>();
    }

    @Override
    public String toString() {
        String res="";
        for (S s: filetable.keySet()
             ) {
            res += s.toString() + "\n";
        }
        return res;
    }

    @Override
    public boolean isDefined(S name) {
        return filetable.containsKey(name);
    }

    @Override
    public void add(S name, B fd) {
        filetable.put(name, fd);
    }

    @Override
    public B get(S v) {
        return filetable.get(v);
    }

    @Override
    public void delete(S v) {
        filetable.remove(v);
    }

    @Override
    public Hashtable<S, B> getAll() {
        return filetable;
    }


}
