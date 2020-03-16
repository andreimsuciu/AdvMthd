package Model.ADTs;

import Model.Values.Value;

import java.util.Hashtable;
import java.util.Map;

public class MyDictionary<T,V> implements MyIDictionary<T,V>{
    private Hashtable<T,V> dictionary;

    public MyDictionary()
    {
        dictionary = new Hashtable<T, V>();
    }

    @Override
    public boolean hasKey(T id) {
        return dictionary.containsKey(id);
    }

    @Override
    public V lookup(T id) {
        return dictionary.get(id);
    }

    @Override
    public void update(T id, V val) {
        dictionary.put(id,val);
    }

    @Override
    public void add(T id, V val) {
        dictionary.put(id,val);
    }

    @Override
    public MyIDictionary<T, V> clone() {
        MyIDictionary<T, V> output = new MyDictionary<>();
        for(T k:this.dictionary.keySet())
            output.add(k, this.lookup(k));
        return output;
    }

    @Override
    public Map<T, V> getContent() {
        return dictionary;
    }

    @Override
    public Hashtable<T, V> getAll() {
        return dictionary;
    }

    @Override
    public String toString() {
        String res="";
        for(T k: dictionary.keySet())
        {
            res+=k.toString()+"->"+dictionary.get(k).toString()+"\n";
        }
        return res;
    }
}
