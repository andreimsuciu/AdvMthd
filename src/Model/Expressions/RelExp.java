package Model.Expressions;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelExp implements Exp {
    Exp e1, e2;
    String ops;
    public RelExp(Exp exp1, Exp exp2, String s)
    {
        e1 = exp1;
        e2 = exp2;
        ops = s;

    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException {
        Value v1 = e1.eval(tbl,hp);
        if(v1.getType().equals(new IntType()))
        {
            Value v2 = e2.eval(tbl,hp);
            if(v2.getType().equals(new IntType()))
            {
                int nr1 = ((IntValue) v1).getVal();
                int nr2 = ((IntValue) v2).getVal();
                switch (ops)
                {
                    case "<":
                        if(nr1 < nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);

                    case "<=":
                        if(nr1 <= nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);

                    case "==":
                        if(nr1 == nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    case "!=":
                        if(nr1 != nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);

                    case ">":
                        if(nr1 > nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    case ">=":
                        if(nr1 >= nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    default:
                            throw new MyException("invalid op");
                }
            }
            else throw new MyException("invalid expression");

        }
        else throw new MyException("invalid expression");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type tExp1 = e1.typeCheck(typeEnv);
        Type tExp2 = e2.typeCheck(typeEnv);
        if(tExp1 instanceof IntType)
            if (tExp2 instanceof IntType)
                return new BoolType();
            else throw new MyException("second operand not int");
        else throw new MyException("first operand not int");
    }

    @Override
    public String toString() {
        return e1.toString()+ops+e2.toString();
    }
}
