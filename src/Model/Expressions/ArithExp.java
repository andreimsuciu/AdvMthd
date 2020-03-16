package Model.Expressions;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.Exceptions.MyException;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    int op; 
    String operation;

    public ArithExp(String opstr, Exp ex1, Exp ex2)
    {
        op = -1;
        e1 = ex1;
        e2 = ex2;
        operation = opstr;
        if(opstr.equals("+"))
            op = 1;
        if(opstr.equals("-"))
            op = 2;
        if(opstr.equals("*"))
            op = 3;
        if(opstr.equals("/"))
            op = 4;
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap hp) throws MyException
    {
        Value v1, v2;
        v1 = e1.eval(tbl,hp);

        if(v1.getType().equals(new IntType()))
        {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int nr1, nr2;
                nr1 = i1.getVal();
                nr2 = i2.getVal();
                if(op == 1)
                    return new IntValue(nr1+nr2);
                if(op==2)
                    return new IntValue(nr1-nr2);
                if(op==3)
                    return new IntValue(nr1*nr2);
                if(op==4)
                {
                    if(nr2==0)
                        throw new MyException("division by 0");
                    else
                        return new IntValue(nr1/nr2);
                }
                else
                    throw new MyException("invalid operand");
            }
            else
                throw new MyException("second op not an int");
        }
        else throw new MyException("first op not an int");
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type tExp1 = e1.typeCheck(typeEnv);
        Type tExp2 = e2.typeCheck(typeEnv);
        if(tExp1 instanceof IntType)
            if (tExp2 instanceof IntType)
                return new IntType();
            else throw new MyException("second op not int");
        else throw new MyException("first op not int");
    }

    @Override
    public String toString() {
        return e1.toString()+ operation + e2.toString();
    }
}
