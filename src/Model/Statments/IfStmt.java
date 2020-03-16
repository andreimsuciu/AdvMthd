package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class IfStmt implements IStmt{

    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el)
    {
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap heap = state.getHeap();
        Value v = exp.eval(symTbl, heap);
        if (!v.getType().equals(new BoolType()))
            throw new MyException("wrong expression!");
        BoolValue bv = (BoolValue) v;
        boolean ok = bv.getVal();
        if (ok){
            state.getExeStack().push(thenS);
        }
        else
            state.getExeStack().push(elseS);
        return null;

    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(exp.typeCheck(typeEnv).equals(new BoolType()))
        {
            thenS.typeCheck(typeEnv.clone());
            elseS.typeCheck(typeEnv.clone());
            return typeEnv;
        }
        else throw new MyException("Condition not a bool expression");
    }

    @Override
    public String toString() {
        return "If (" + exp.toString() + ") then (" + thenS.toString() + ") else (" + elseS.toString() +")";
    }
}
