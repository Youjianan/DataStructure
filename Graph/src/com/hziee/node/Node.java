package com.hziee.node;

public class Node<T> {

    public T data;//数据域,包含系数、指数的数组
    public Node<T> next;//地址域,应用后继结点

    public Node(){
        this(null,null);
    }

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString(){
        return this.data.toString();
    }
}
