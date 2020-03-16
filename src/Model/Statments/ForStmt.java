package Model.Statments;

import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIStack;
import Model.ADTs.PrgState;
import Model.Exceptions.MyException;
import Model.Expressions.Exp;
import Model.Expressions.RelExp;
import Model.Expressions.VarExp;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;

public class ForStmt implements IStmt {
	private String var;
    private Exp init;
    private Exp cond;
    private Exp inc;
    private IStmt stmt;
    
	public ForStmt(String variable, Exp initialization, Exp condition, Exp increment, IStmt statement) {
		this.var=variable;
		this.init=initialization;
		this.cond=condition;
		this.inc=increment;
		this.stmt=statement;
	}

	@Override
	public PrgState execute(PrgState state) throws MyException {
		MyIStack<IStmt> exeStack = state.getExeStack();
        IStmt newStmt =  
        		new CompStmt(new AssignStmt(var, init), new WhileStmt(new RelExp(cond, new VarExp("v"), ">"),
                new CompStmt(stmt, new AssignStmt(var, inc))));
        exeStack.push(newStmt);
        state.setExeStack(exeStack);
        return null;
	}

	@Override
	public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
		 Type typeExp1 = init.typeCheck(typeEnv);
		 Type typeExp2 = cond.typeCheck(typeEnv);
		 Type typeExp3 = inc.typeCheck(typeEnv);
		 Type typeExp4 = typeEnv.lookup(var);
	        if(typeExp1 instanceof IntType)
	        {
	        	if(typeExp2 instanceof IntType)
	        	{
	        		if(typeExp3 instanceof IntType)
	        		{
	        			if(typeExp4 instanceof IntType) 
	        				{
	        				stmt.typeCheck(typeEnv.clone());
	        				return typeEnv;
	        				}
	        			else throw new MyException("variable not of type int");
	        		}
	        		else throw new MyException("increment result not int");
	        	}
	        	else throw new MyException("condition not int");
	        }
	        else throw new MyException("initialization not int");
	}
	public String toString() {
        return "for(" + var + "=" + init.toString() + ";" + cond.toString() + ";" + inc.toString()+ ")" + stmt.toString()+ "; ";
    }
}
