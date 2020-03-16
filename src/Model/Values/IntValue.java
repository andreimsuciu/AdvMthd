package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value {

    int val;
    public IntValue(int v)
    {
        val = v;
    }

    public int getVal()
    {
        return val;
    }

    @Override
    public boolean equals(Object another)
    {
        if(another instanceof IntValue) {
            if (((IntValue) another).getVal() == val)
                return true;
            else
                return false;
        }
        else return false;
    }

    @Override
    public String toString() {
        return (String.valueOf(val));
    }

    @Override
    public Type getType()
    {
        return new IntType();
    }
}
