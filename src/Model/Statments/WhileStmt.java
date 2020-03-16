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

public class WhileStmt implements IStmt {

    Exp exp;
    IStmt st;
    public WhileStmt(Exp e, IStmt s)
    {
        exp = e;
        st = s;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap heap = state.getHeap();
        Value v = exp.eval(symTbl, heap);
        if(v.getType().equals(new BoolType()))
        {
            BoolValue bv = (BoolValue) v;
            if(bv.getVal() == true)
            {
                state.getExeStack().push(new WhileStmt(exp, st));
                state.getExeStack().push(st);
            }
            return null;
        }
        else throw new MyException("invalid type");
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typeCheck(typeEnv);
        if(typeExp instanceof BoolType)
        {
            st.typeCheck(typeEnv.clone());
            return typeEnv;
        }
        else throw new MyException("While condition not a bool expression");
    }

    @Override
    public String toString() {
        return "while("+exp.toString()+")("+st.toString()+")";
    }
}
