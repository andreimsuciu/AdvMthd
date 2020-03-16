package Model.Expressions;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    int op; // 1 && 2 ||
    String ope;
    public LogicExp(String ops, Exp e11, Exp e22)
    {
        e1 = e11;
        e2 = e22;
        op = -1;
        ope = ops;
        if(ops.equals("&&"))
            op = 1;
        if(ops.equals("||"))
            op = 2;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap hp) throws MyException
    {

        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        if(v1.getType().equals(new BoolType()))
        {
            v2 = e2.eval(tbl, hp);
            if(v2.getType().equals(new BoolType()))
            {
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                Boolean b1,b2;
                b1 = i1.getVal();
                b2 = i2.getVal();

                if(op==1)
                    return new BoolValue(b1 && b2);
                else if(op==2)
                    return new BoolValue(b2||b1);
                else
                    throw new MyException("invalid operand");
            }
            else
                throw new MyException("second operator is not boolean");
        }
        else
            throw new MyException("first operator is not boolean");

    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type tExp1 = e1.typeCheck(typeEnv);
        Type tExp2 = e2.typeCheck(typeEnv);
        if(tExp1 instanceof BoolType)
            if (tExp2 instanceof BoolType)
                return new BoolType();
            else throw new MyException("second operand not bool");
        else throw new MyException("first operand not bool");
    }

    @Override
    public String toString() {
        return e1.toString() + ope + e2.toString();
    }
}
