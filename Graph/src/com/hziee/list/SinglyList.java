package com.hziee.list;

import com.hziee.node.Node;

public class SinglyList<T> {
    private int size;
    public Node<T> head;//头指针，指向头结点

    public SinglyList() {
        this.head = new Node<>();
        size = 0;
    }

    public SinglyList(T[] values) {
        this();
        Node<T> rear = this.head;//rear指向链表的尾结点
        for (T value : values) {
            rear.next = new Node<>(value, null);//尾插入
            rear = rear.next;//rear指向下一个结点
            size++;
        }
    }

    //深拷贝
    public SinglyList(SinglyList<T> list) {
        this();
        //通过创建nodes数组获取list中的结点
        this.size = list.size;
        Node[] nodes = new Node[size];
        Node<T> p = list.head.next;
        int i = 0;
        while (p != null && i < size) {
            nodes[i] = new Node<>(p.data, p.next);
            p = p.next;
            i++;
        }
        //赋予当前list
        Node<T> head = this.head;
        for (i = 0; i < size; i++) {
            head.next = nodes[i];
            head = head.next;
        }
    }

    public boolean isEmpty() {
        return this.head.next == null;//判断head头指针的后继结点引用是否为null
    }

    /**
     * 取出index处的结点对象
     *
     * @param index 索引
     * @return 结点对象
     * 无容错措施
     */
    private Node<T> getNode(int index) {
        Node<T> p = this.head.next;//初始将p指向第0个结点
        for (int i = 0; p != null && i < index; i++) p = p.next;//执行index次[p = p.next]操作,完成后p即指向index处的结点
        //执行到此处p指向index处结点
        return (index >= 0 && index < size()) ? p : null;//防止index为负时的错误调用
    }

    //获取方法
    public T get(int index) {
        Node<T> p = this.getNode(index);
        return (p != null) ? p.data : null;
    }

    //赋值方法
    public void set(int index, T data) {
        Node<T> p = this.getNode(index);
        if (data != null && p != null) p.data = data;
    }

    public int size() {
        /*Node<T> p = this.head;//指向头指针
        int size = 0;
        while (p.next != null){
            size++;
            p = p.next;
        }*/
        return size;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Node<T> p = this.head.next; p != null; p = p.next) {
            str.append(p.data.toString());
            if (p.next != null) str.append(",");
        }
        return str.toString();
    }

    /**
     * 插入方法
     *
     * @param index 插入处索引
     * @param data  索引
     * @return 插入的结点对象
     * 容错:index > size 插入到最后一个
     */
    public Node<T> insert(int index, T data) {
        if (data == null) throw new NullPointerException("data==null");
        Node<T> front = this.head;
        for (int i = 0; front.next != null && i < index; i++) front = front.next;//front指向[index-1]处的结点
        front.next = new Node<>(data, front.next);//注意事项:第二个[front.next]为[index-1]结点处的原后继结点
        size++;
        return front.next;
    }

    public Node<T> insert(T data) {
        /*return this.insert(Integer.MAX_VALUE,data);*/
        return this.insert(size, data);
    }

    /**
     * 插入不同元素,尾插入
     *
     * @param data 要插入的元素
     * @return
     */
    public Node<T> insertDifferent(T data) {
        if (this.contains(data)) return null;
        Node<T> p = this.head.next;
        while (p.next != null) p = p.next;//p指向最后一个结点
        p.next = new Node<>(data, null);
        size++;
        return p.next;
    }

    /**
     * 删除第一个与key相同的元素
     *
     * @param key
     * @return
     */
    public T remove(T key) {
        Node<T> front = this.head;
        for (int index = 0; index < size; index++) {
            if (front.next.data.equals(key)) break;
            front = front.next;
        }
        T data = front.next.data;
        front.next = front.next.next;
        size--;
        return data;
    }

    /**
     * 删除指定索引处的结点
     *
     * @param index 指定索引
     * @return 删除的结点内容
     */
    public T remove(int index) {
        Node<T> front = this.head;
        for (int i = 0; front.next != null && i < index; i++) front = front.next;//寻找第i-1个元素
        if (index >= 0 && front.next != null) {//如果有后继元素
            T old = front.next.data;
            front.next = front.next.next;//关键
            size--;
            return old;
        }
        return null;
    }

    //清除方法
    public void clear() {
        this.head.next = null;
        size = 0;
    }

    /**
     * 查找首个与key相同的元素
     *
     * @param key
     * @return
     */
    public Node<T> search(T key) {
        for (Node<T> p = this.head.next; p != null; p = p.next) {
            if (p.data.equals(key)) return p;
        }
        return null;
    }

    public boolean contains(T key) {
        if (this.search(key) != null) return true;
        return false;
    }

    //逆转方法
    public void reverse() {
        Node<T> front = null;//初始将front置为null
        Node<T> p = this.head.next;//初始p指向第0个结点
        Node<T> temp;//储存下一结点
        while (p != null) {//当p为null时,说明位于尾结点
            temp = p.next;//事先储存下一结点

            p.next = front;//关键:指向反转

            front = p;//指针后移
            p = temp;//此时p指向下一结点
        }
        //执行到此处front指向最后一个结点
        head.next = front;//将尾结点[此时为front]赋予头指针的下一位
    }
}
