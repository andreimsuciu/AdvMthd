package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Types.Type;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt second;


    public CompStmt(IStmt f, IStmt s)
    {
        first = f;
        second = s;
    }

    @Override
    public PrgState execute(PrgState state)
    {
        MyIStack<IStmt> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return second.typeCheck(first.typeCheck(typeEnv));
    }

    @Override
    public String toString() {
        return first.toString()+";"+second.toString();
    }
}
