package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Types.Type;

public class NopStmt implements IStmt{
    public NopStmt()
    {

    }

    @Override
    public PrgState execute(PrgState state)
    {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "NOP";
    }
}
