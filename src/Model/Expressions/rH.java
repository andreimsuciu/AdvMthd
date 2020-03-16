package Model.Expressions;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Statments.IStmt;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;


public class rH implements Exp {
    Exp exp;
    public rH(Exp e)
    {
        exp = e;
    }
    @Override
    public String toString() {
        return "rH("+exp.toString()+")";
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException {
        Value v = exp.eval(tbl,hp);
        if(v instanceof RefValue)
        {
            RefValue rv = (RefValue) v;
            int addr = rv.getAddr();
            if(hp.isKey(addr))
            {
                return hp.getValue(addr);
            }
            else {throw new MyException("Invalid address");}
        }
        else throw new MyException("not a ref value");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ = exp.typeCheck(typeEnv);
        if(typ instanceof RefType)
        {
            RefType refType = (RefType) typ;
            return refType.getInner();
        }
        else
            throw new MyException("rH argument not a Ref Type");
    }
}
