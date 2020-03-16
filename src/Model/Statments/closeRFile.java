package Model.Statments;

import Model.ADTs.*;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    Exp exp;

    public closeRFile(Exp e)
    {
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIFileTable<StringValue, BufferedReader>  fileTable = state.getFileTable();
        MyIHeap heap = state.getHeap();
        Value v = exp.eval(symTable, heap);
        if(v.getType().equals(new StringType()))
        {
            if(fileTable.isDefined((StringValue) v))
            {
                BufferedReader fd = fileTable.get((StringValue)v);
                try {
                    fd.close();
                    fileTable.delete((StringValue) v);
                }
                catch (IOException e)
                {
                    throw new MyException("invalid close!");
                }

            }
            else throw new MyException("invalid filename");

        }
        else throw new MyException("invalid filename");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(exp.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else throw new MyException("wrong type of argument");
    }

    @Override
    public String toString() {
        return "closeRFile("+exp.toString()+")";
    }
}
