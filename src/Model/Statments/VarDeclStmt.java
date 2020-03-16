package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class VarDeclStmt implements IStmt{
    String id;
    Type type;

    public VarDeclStmt(String v, Type e)
    {
        id = v;
        type = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException
    {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if(symTbl.hasKey(id))
            throw new MyException(id + " is already defined");
        symTbl.add(id, type.defaultValue());

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.add(id, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type.toString()+" "+id;
    }
}
