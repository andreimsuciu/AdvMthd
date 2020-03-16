package Model.ADTs;

import Model.Statments.IStmt;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack;

    public MyStack()
    {
        stack = new Stack<T>();
    }

    @Override
    public T pop()
    {
        return stack.pop();
    }

    @Override
    public void push(T value)
    {
        stack.push(value);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Stack<T> getAll() {
        return stack;
    }

    @Override
    public String toString() {
        String res="";
        int index = stack.size()-1;
        for(;index>=0;--index){
            res=res+stack.get(index).toString()+"\n";
        }
        return res;
    }
}
