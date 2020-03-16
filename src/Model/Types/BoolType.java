package Model.Types;

import Model.Values.BoolValue;
import Model.Values.Value;

public class BoolType implements Type{

    public BoolType(){}

    @Override
    public boolean equals(Object another){
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }

    @Override
    public String toString() { return "bool";}

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
