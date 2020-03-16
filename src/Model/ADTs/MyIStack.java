package Model.ADTs;

import Model.Statments.IStmt;

import java.util.Stack;

public interface MyIStack<T> {
    public T pop();
    public void push(T value);
    boolean isEmpty();
    Stack<T> getAll();
}
