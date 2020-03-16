package Model.Values;

import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;

public class BoolValue implements Value {

    boolean val;
    public BoolValue(boolean v)
    {
        val = v;
    }

    public boolean getVal()
    {
        return val;
    }

    @Override
    public boolean equals(Object another)
    {
        if(another instanceof BoolValue) {
            if (((BoolValue) another).getVal() == val)
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
        return new BoolType();
    }
}
