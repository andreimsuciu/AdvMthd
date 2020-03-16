package Model.Expressions;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExp implements Exp {
    Value e;

    public ValueExp(Value v)
    {
        e = v;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException
    {
        return e;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    @Override
    public String toString() {
        return e.toString();
    }
}
