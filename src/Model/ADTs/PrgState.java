package Model.ADTs;

import Model.Exceptions.MyException;
import Model.Statments.IStmt;
import Model.Values.StringValue;
import Model.Values.Value;
import View.Utils.Addresses;

import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIFileTable<StringValue, BufferedReader> fileTable;
    MyIHeap heap;
    IStmt originalProgram; //optional field, but good to have
    private MyIDictionary<Integer, Integer> lockTable;
    private Addresses lockAddress = new Addresses();
    static int id = 1;
    int prgId;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> table, MyIList<Value> ot, 
    		MyIFileTable<StringValue, BufferedReader>  fileTbl, MyIHeap hp, IStmt stmt,MyIDictionary<Integer, Integer> lckTbl)
    {
        exeStack=stk;
        symTable=table;
        out = ot;
        fileTable = fileTbl;
        heap = hp;
        prgId = id;
        lockTable=lckTbl;
        stk.push(stmt);
    }
    
    public MyIDictionary<Integer, Integer> getLockTable() {
        return lockTable;
    }

    public void setLockTable(MyIDictionary<Integer, Integer> lockTable) {
        this.lockTable = lockTable;
    }

    public Integer getLockAddress() { return lockAddress.getFreeAddress(); }
    
    public int getPrgId()
    {
        return prgId;
    }

    public MyIStack<IStmt> getExeStack()
    {
        return exeStack;
    }

    public void setFileTable(MyIFileTable<StringValue, BufferedReader>  ftbl) {fileTable = ftbl;}

    public MyIFileTable<StringValue, BufferedReader>  getFileTable() {return fileTable;}

    public void setExeStack(MyIStack<IStmt> stk)
    {
        exeStack = stk;
    }

    public MyIDictionary<String, Value> getSymTable()
    {
        return symTable;
    }

    public void setSymTable (MyIDictionary<String, Value> dict)
    {
        symTable = dict;
    }

    public MyIList<Value> getOut()
    {
        return out;
    }

    public void setOut(MyIList<Value> ot)
    {
        out = ot;
    }

    public MyIDictionary<String, Value> cloneSymTbl()
    {
        MyIDictionary<String, Value> symTbl = new MyDictionary<>();
        for(String key : symTable.getContent().keySet())
        {
            symTbl.add(key, symTable.lookup(key));
        }
        return symTbl;
    }

    public MyIHeap getHeap()
    {
        return heap;
    }

    public void setHeap(MyIHeap h)
    {
        heap = h;
    }

    @Override
    public String toString() {
        return "Id=" + String.valueOf(prgId) + "\nExeStack:\n" + exeStack.toString()
                +"SymTable_"+String.valueOf(prgId)+":\n"+ symTable.toString()+"Output:\n"+out.toString()+
                "FileTable:\n"+fileTable.toString()+"Heap:\n"+heap.toString()+"------------------\n";
    }

    public boolean isNotCompleted()
    {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException
    {
        if(exeStack.isEmpty())
            throw new MyException("execution stack empty!");
        IStmt stm = exeStack.pop();
        return stm.execute(this);
    }

    public static synchronized void setId()
    {
        id++;
    }

    public PrgState get() {
        return this;
    }

	
}

