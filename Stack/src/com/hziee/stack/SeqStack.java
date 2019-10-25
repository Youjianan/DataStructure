package com.hziee.stack;

import com.hziee.list.SeqList;

public final class SeqStack<T> implements Stack<T> {
    private SeqList<T> list;

    public SeqStack(int length) {
        this.list = new SeqList<>(length);
    }

    public SeqStack() {
        this(64);
    }

    /*判断栈是否为空*/
    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /*元素x入栈*/
    @Override
    public void push(T x) {
        this.list.insert(x);
    }

    /*不出栈，返回栈顶元素*/
    @Override
    public T peek() {
        return this.list.get(list.size()-1);
    }

    /*出栈，返回栈顶元素*/
    @Override
    public T pop() {
        return this.list.remove(list.size()-1);
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
