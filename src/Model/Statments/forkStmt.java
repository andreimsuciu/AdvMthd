package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyStack;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Types.Type;

public class forkStmt implements IStmt {

    IStmt stmt;

    public forkStmt(IStmt st)
    {
        stmt = st;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        PrgState.setId();
        PrgState newState = new PrgState(new MyStack<IStmt>(), state.cloneSymTbl(), state.getOut(), state.getFileTable(), state.getHeap(), stmt, state.getLockTable());
        return newState;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        stmt.typeCheck(typeEnv.clone());
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork("+stmt.toString()+")";
    }
}
