package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStmt implements IStmt{
    String id;
    Exp exp;

    public AssignStmt(String v, Exp e)
    {
        id = v;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIStack<IStmt> stack = state.getExeStack();
        MyIHeap heap = state.getHeap();

        if (symTbl.hasKey(id)) {
            Value val = exp.eval(symTbl, heap);
            Type typeId = (symTbl.lookup(id)).getType();
            if(val.getType().equals(typeId))
                symTbl.update(id, val);
            else
                throw new MyException("invalid type for the expression");
        }
        else throw new MyException("the used variable "+id+"was not declared");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(id);
        Type typExp = exp.typeCheck(typeEnv);
        if (typeVar.equals(typExp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }
}
