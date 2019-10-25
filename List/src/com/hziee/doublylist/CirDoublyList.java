package com.hziee.doublylist;

import com.hziee.node.DoubleNode;

public class CirDoublyList<T> {
    private int size;
    private DoubleNode<T> head;

    /*构造空循环双链表*/
    public CirDoublyList() {
        head = new DoubleNode<>();
        head.prev = head;
        head.next = head;
        size = 0;
    }

    /*有参构造，由数组提供元素*/
    public CirDoublyList(T[] values) {
        this();
        DoubleNode<T> last = head;
        for (T value : values) {
            last.next = new DoubleNode<>(value, last, head);
            head.prev = last.next;
            last = last.next;
            size++;
        }
    }

    /*深拷贝构造*/
    public CirDoublyList(CirDoublyList<T> list) {
        this();
        DoubleNode<T> addNode = list.head;
        while (addNode.next != list.head) {
            addNode = addNode.next;
            this.add(addNode.data);
        }
    }

    /*获取i处的数据*/
    public T get(int i) {
        if (isEmpty() || !checkIndex(i))
            return null;
        DoubleNode<T> target = getNodeById(i);
        return target.data;
    }

    /*设置i处的数据*/
    public void set(int i, T data) {
        if (!checkIndex(i))
            throw new IndexOutOfBoundsException("index < 0 or index > size-1");
        if (data == null)
            throw new NullPointerException("set data is null");
        DoubleNode<T> target = getNodeById(i);
        target.data = data;
    }

    /**
     * 在i处添加结点
     * @param i 添加的位置
     * @param data 添加的数据
     * @return 返回成功添加的结点
     */
    public DoubleNode<T> add(int i, T data) {
        if (data == null)
            throw new NullPointerException("add data is null");
        DoubleNode<T> target = getNodeById(i);
        DoubleNode<T> addNode = new DoubleNode<>(data, target.prev, target);
        target.prev.next = addNode;
        target.prev = addNode;
        size++;
        return addNode;
    }

    /**
     * 尾插入数据
     * @param data 插入的数据
     * @return 返回成功添加的结点
     */
    public DoubleNode<T> add(T data) {
        if (data == null)
            throw new NullPointerException("add data is null");
        DoubleNode<T> addNode = new DoubleNode<>(data, head.prev, head);
        head.prev.next = addNode;
        head.prev = addNode;
        size++;
        return addNode;
    }

    /**
     * 添加数据不重复的结点
     * @param data 要添加的数据
     * @return 返回成功添加的结点
     */
    public DoubleNode<T> addDifferent(T data) {
        if (search(data) != null)
            return null;
        return add(data);
    }

    /*合并连接*/
    public void addAll(CirDoublyList<T> list) {
        DoubleNode<T> addNode = list.head;
        while (addNode.next != list.head) {
            addNode = addNode.next;
            this.add(addNode.data);
        }
    }

    /**
     * 删除i处的结点
     * @param i 删除的位置
     * @return 返回删除掉的结点的数据
     */
    public T remove(int i) {
        if (isEmpty() || !checkIndex(i))
            return null;
        DoubleNode<T> target = getNodeById(i);
        target.prev.next = target.next;
        target.next.prev = target.prev;
        size--;
        return target.data;
    }

    /**
     * 删除第一个数据与key匹配的结点
     * @param key 要删除的结点的数据
     * @return 返回删除掉的结点的数据
     */
    public T remove(T key) {
        if (search(key) == null)
            return null;
        DoubleNode<T> target = search(key);
        target.prev.next = target.next;
        target.next.prev = target.prev;
        size--;
        return target.data;
    }

    /**
     * 查找第一个与key匹配的结点
     * @param key 要查找的数据
     * @return 返回查找到的结点
     */
    public DoubleNode<T> search(T key) {
        DoubleNode<T> target = head;
        while (target.next != head) {
            target = target.next;
            if (key.equals(target.data))
                return target;
        }
        return null;
    }

    /**
     * 删除最后一个元素
     * @return 删除掉的结点的数据
     */
    public T removeLast() {
        if (isEmpty())
            return null;
        DoubleNode<T> last = head.prev;
        last.next.prev = last.prev;
        last.prev.next = last.next;
        size--;
        return last.data;
    }

    /*返回链表容量*/
    public int size() {
        return size;
    }

    /*判断循环双链表是否为空*/
    public boolean isEmpty() {
        return head.next == head;
    }

    /*判断索引是否在链表长度范围内*/
    private boolean checkIndex(int i) {
        return i >= 0 && i <= size-1;
    }

    /*获取索引位置处的结点*/
    private DoubleNode<T> getNodeById(int i) {
        DoubleNode<T> target = head.next;
        for (int j = 0; target != head && j < i; j++)
            target = target.next;
        return target;
    }

    /*清空链表数据*/
    public void clear() {
        head.next = head;
        head.prev = head;
        size = 0;
    }

    /*打印链表所有数据*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getName()).append(" {");
        for (DoubleNode<T> p = head.next; p != head; p = p.next) {
            sb.append(p.data.toString());
            if (p.next != head)
                sb.append(",");
        }
        return sb.append("}").toString();
    }
}
