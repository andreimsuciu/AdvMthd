package Model.ADTs;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private ArrayList<T> list;

    public MyList()
    {
        list = new ArrayList<T>();
    }

    @Override
    public void addToList(T e) {
        list.add(e);
    }

    @Override
    public ArrayList<T> getAll() {
        return list;
    }

    @Override
    public String toString() {
        String res="";
        for(T elem: list)
        {
            res+=elem.toString()+"\n";
        }
        return res;
    }
}
