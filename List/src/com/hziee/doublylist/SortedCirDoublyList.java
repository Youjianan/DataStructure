package com.hziee.doublylist;

import com.hziee.node.DoubleNode;

public class SortedCirDoublyList<T extends Comparable<? super T>> extends CirDoublyList<T> {
    public SortedCirDoublyList() {
        super();
    }

    public SortedCirDoublyList(T[] values) {

    }

    public SortedCirDoublyList(CirDoublyList<T> list) {

    }

    public SortedCirDoublyList(SortedCirDoublyList<T> list) {

    }

    @Override
    public DoubleNode<T> add(T data) {
        return super.add(data);
    }

    @Override
    public DoubleNode<T> addDifferent(T data) {
        return super.addDifferent(data);
    }

    @Override
    public void addAll(CirDoublyList<T> list) {
        super.addAll(list);
    }

    @Override
    public DoubleNode<T> search(T key) {
        return super.search(key);
    }

    /*不支持使用的方法并抛出异常*/
    @Override
    public void set(int i, T data) {
        throw new UnsupportedOperationException("不支持使用此方法");
    }
    @Override
    public DoubleNode<T> add(int i, T data) {
        throw new UnsupportedOperationException("不支持使用此方法");
    }
}
