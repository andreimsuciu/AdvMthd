package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.sql.Ref;

public class newH implements IStmt {
    String var_name;
    Exp exp;

    public newH(String var, Exp e)
    {
        var_name = var;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if(symTbl.hasKey(var_name))
        {
            if(symTbl.lookup(var_name).getType() instanceof RefType)
            {
                Value v = exp.eval(symTbl, heap);
                RefType t = (RefType) symTbl.lookup(var_name).getType();
                if(v.getType().equals(t.getInner()))
                {
                    heap.add(v);
                    symTbl.add(var_name, new RefValue(v.getType(),heap.getCurrentKey()));
                    return null;
                }
                else throw new MyException("invalid type");
            }
            else
                throw new MyException("not a ref type");
        }
        else throw new MyException("variable is not defined");
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(var_name);
        Type typeExp = exp.typeCheck(typeEnv);
        if(typeVar.equals(new RefType(typeExp)))
            return typeEnv;
        else
            throw new MyException("New stmt: different types");

    }

    @Override
    public String toString() {
        return "newH("+var_name+", "+exp.toString()+")";
    }
}
