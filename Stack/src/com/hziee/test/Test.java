package com.hziee.test;

import com.hziee.stack.SeqStack;

public class Test {
    public static void main(String[] args) {
        SeqStack<String> stack = new SeqStack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.pop();
        System.out.println(stack.peek());
        System.out.println(stack);
    }
}
