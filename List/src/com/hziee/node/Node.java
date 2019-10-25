package com.hziee.node;

public class Node<City> {
    public City data;
    public Node<City> next;

    public Node() {
        this(null,null);
    }

    public Node(City data, Node<City> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
