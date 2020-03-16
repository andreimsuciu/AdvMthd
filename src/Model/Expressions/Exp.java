package Model.Expressions;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.Type;
import Model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException;
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
