package com.hziee.queue;

public interface Queue<T> {
    boolean isEmpty();
    boolean add(T x);   //入队
    T peek();           //返回队头元素
    T poll();           //出队
}
