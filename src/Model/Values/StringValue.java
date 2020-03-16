package Model.Values;

import Model.Types.StringType;
import Model.Types.Type;

public class StringValue implements Value {

    String val;
    public StringValue(String v)
    {
        val = v;
    }

    public String getVal()
    {
        return val;
    }

    @Override
    public boolean equals(Object another)
    {
        if(another instanceof StringValue) {
            if (((StringValue) another).getVal() == val)
                return true;
            else
                return false;
        }
        else return false;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public String toString() {
        return val;
    }
}
