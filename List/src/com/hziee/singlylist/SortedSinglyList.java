package com.hziee.singlylist;/*
package com.hziee.singlylist;

import com.hziee.node.Node;

public class SortedSinglyList<T extends Comparable<? super T>> extends SinglyList<T> {
    */
/*创建空链表*//*

    public SortedSinglyList() {
        super();
    }

    */
/*有参构造，由values数组提供元素*//*

    public SortedSinglyList(T[] values) {
        super();
        for (T value : values)
            this.add(value);
    }

    */
/*调用父类的深拷贝构造*//*

    public SortedSinglyList(SortedSinglyList<T> list) {
        super(list);
    }

    */
/*由单链表构造排序单链表*//*

    public SortedSinglyList(SinglyList<T> list) {
        super();
        for (Node<T> p = list.head.next; p != null; p = p.next)
            this.add(p.data);
    }

    */
/*不支持父类的以下两个方法，将其覆盖并抛出异常*//*

    @Override
    public void set(int i, T data) {
        throw new UnsupportedOperationException("不支持使用此方法");
    }
    @Override
    public Node<T> add(int i, T data) {
        throw new UnsupportedOperationException("不支持使用此方法");
    }

    */
/*顺序查找确定插入位置，返回插入结点*//*

    @Override
    public Node<T> add(T data) {
        Node<T> front = this.head;
        Node<T> p = front.next;
        while (p != null && data.compareTo(p.data) > 0) {
            front = p;
            p = p.next;
        }
        front.next = new Node<T>(data,p);
        return front.next;
    }
}*/
