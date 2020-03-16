package Model.Statments;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class lock implements IStmt {
	private String var;
	private static Lock lock = new ReentrantLock();

	public lock(String vr){
	    	var = vr;
	    }	
	@Override
	public PrgState execute(PrgState state) throws MyException {
		lock.lock();
		Value foundIndex = state.getSymTable().lookup(var);

        if(foundIndex == null)
            throw new MyException("No such variable in symbolTable");
        IntValue foundIndexIntValue=(IntValue)foundIndex;
        Integer foundIndexInt=foundIndexIntValue.getVal();
        MyIDictionary<Integer, Integer> lockTable = state.getLockTable();
        Integer lockValue = lockTable.lookup(foundIndexInt);
        if (lockValue == null)
            throw new MyException("No such index in lockTable");
        if (lockValue == -1) {
            lockTable.add(foundIndexInt, state.getPrgId());
            state.setLockTable(lockTable);
        }
        else{
            MyIStack<IStmt> exeStack = state.getExeStack();
            exeStack.push(this);
            state.setExeStack(exeStack);
        }

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
        return "lock( " + var + " )";
    }
}
