package com.hziee.stack;

public interface Stack<T> {
    boolean isEmpty();   //判断栈是否为空
    void push(T x);      //元素x入栈
    T peek();            //未出栈，返回栈顶元素
    T pop();             //出栈，返回栈顶元素
}
