package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIFileTable;
import Model.ADTs.MyIHeap;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt {
    Exp exp;
    String var_name;
    public readFile(Exp e, String name)
    {
        exp = e;
        var_name = name;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(!symTable.lookup(var_name).getType().equals(new IntType()))
            throw new MyException("invalid type");
        if(symTable.hasKey(var_name)) {
            MyIFileTable<StringValue, BufferedReader>  fileTable = state.getFileTable();
            MyIHeap heap = state.getHeap();
            Value v = exp.eval(symTable, heap);
            if (v.getType().equals(new StringType())) {
                BufferedReader fd = fileTable.get((StringValue) v);
                if (fd == null)
                    throw new MyException("invalid fd");
                try {
                    String read = fd.readLine();
                    IntValue nr;
                    if (read == null)
                        nr = new IntValue(0);
                    else
                        nr = new IntValue(Integer.parseInt(read));
                    symTable.add(var_name,nr);
                } catch (IOException e) {
                    throw new MyException("invalid reading");
                }

            } else throw new MyException("invalid filename");
        }
        else
            throw new MyException("invalid var name");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeVar = typeEnv.lookup(var_name);
        Type typeExp = exp.typeCheck(typeEnv);
        if(typeExp.equals(new StringType()))
            return typeEnv;
        else throw new MyException("ReadFile: not a string");
    }

    @Override
    public String toString() {
        return "readFile("+exp.toString()+")";
    }
}
