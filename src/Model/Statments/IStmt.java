package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Types.Type;

public interface IStmt {
    PrgState execute (PrgState state) throws MyException;
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> typeEnv) throws MyException;

}
