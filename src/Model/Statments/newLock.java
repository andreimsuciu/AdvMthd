package Model.Statments;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Model.ADTs.MyIDictionary;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class newLock implements IStmt {
	private String var;
    private static Lock lock = new ReentrantLock();
    
	public newLock(String vr) {
		var=vr;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		lock.lock();
        MyIDictionary<Integer, Integer> lockTbl = state.getLockTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();

        Integer location = state.getLockAddress();

        lockTbl.add(location, -1);
        Value loc = new IntValue(location);
        symTbl.add(var, loc);

        state.setSymTable(symTbl);
        state.setLockTable(lockTbl);
        lock.unlock();
        return null;
	}

	@Override
	public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		Type typeExp = typeEnv.lookup(var);
		if(typeExp instanceof IntType) {
			return typeEnv;
		}else throw new MyException("variable not of type int");
	}
	
	@Override
    public String toString(){
        return "newLock( " + var + " )";
    }

}
