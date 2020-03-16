package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIFileTable;
import Model.ADTs.MyIHeap;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.IOException;

public class openRFile implements IStmt{
    Exp exp;
    public openRFile(Exp e)
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
            StringValue name = (StringValue) v;
            if(!fileTable.isDefined(name))
            {
                try {
                    BufferedReader fd = new BufferedReader(new FileReader((String) name.getVal()));
                    fileTable.add(name, fd);
                }
                catch (FileNotFoundException e){
                    throw new MyException("file not found!");
                }
            }
            else throw new MyException("name already defined!");
        }
        else throw new MyException("invalid filename!");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        if(exp.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyException("Open file: not a string");
    }


    @Override
    public String toString() {
        return "openRFile("+exp.toString()+")";
    }
}
