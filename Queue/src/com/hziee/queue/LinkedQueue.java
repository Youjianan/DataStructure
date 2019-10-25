package com.hziee.queue;

import com.hziee.node.Node;

public final class LinkedQueue<T> implements Queue<T> {
    private Node<T> front, rear;
    private int size;

    public LinkedQueue() {
        this.front = this.rear = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /*入队*/
    @Override
    public boolean add(T data) {
        if (data == null)
            return false;
        Node<T> node = new Node<>(data, null);
        if (this.front == null)
            this.front = node;
        else this.rear.next = node;
        this.rear = node;
        size++;
        return true;
    }

    /*返回队头元素*/
    @Override
    public T peek() {
        return this.isEmpty() ? null : this.front.data;
    }

    /*出队*/
    @Override
    public T poll() {
        if (isEmpty())
            return null;
        T data = this.front.data;
        this.front = this.front.next;
        if (this.front == null)
            this.rear = null;
        size--;
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LinkedQueue[\n");
        for (Node<T> p = front; p != null; p = p.next) {
            sb.append(p.data);
            sb.append("\n");
        }
        return sb.append("]").toString();
    }
}
