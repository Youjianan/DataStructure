package com.hziee.singlylist;

import com.hziee.model.City;
import com.hziee.node.Node;

public class SinglyList {
    private int size;        //链表长度
    Node<City> head;         //头结点

    /*创建空链表*/
    public SinglyList() {
        head = new Node<>();
        size = 0;
    }

    /*有参构造，由数组提供数据*/
    public SinglyList(City[] values) {
        this();
        Node<City> rear = head;
        for (City value : values) {
            rear.next = new Node<>(value,null);
            rear = rear.next;
            size++;
        }
    }

    /*深拷贝构造*/
    public SinglyList(SinglyList list) {
        this();
        Node<City> addNode = list.head;
        while (addNode.next != null) {
            addNode = addNode.next;
            this.add(addNode.data);
        }
    }

    /*根据城市名获取城市信息*/
    public City findCityByName(String name) {
        for (Node<City> front = head; front.next != null; front = front.next) {
            City city = front.next.data;
            if (name.equals(city.getName()))
                return city;
        }
        System.out.println("找不到该城市名");
        return null;
    }

    /*根据位置坐标获取城市信息*/
    public City findCityByPosition(double x, double y) {
        for (Node<City> front = head; front.next != null; front = front.next) {
            City city = front.next.data;
            if (city.getX() == x && city.getY() == y)
                return city;
        }
        System.out.println("找不到该位置坐标的城市");
        return null;
    }

    /**
     * 给定一个位置坐标(x,y)和一个距离d，返回所有与(x,y)的距离小于等于d的城市。
     * @param x 横坐标
     * @param y 纵坐标
     * @param d 距离
     * @return 返回所有与(x,y)的距离小于等于d的城市
     */
    public String findCitiesByDistance(double x, double y, double d) {
        StringBuilder sb = new StringBuilder();
        for (Node<City> front = head; front.next != null; front = front.next) {
            City city = front.next.data;
            if (Math.sqrt(Math.pow(city.getX() - x, 2) + Math.pow(city.getY() - y, 2)) <= d)
                sb.append(city);
        }
        return sb.toString();
    }

    /*插入一条城市信息*/
    public void insertCity(String name, double x, double y) {
        Node<City> front = head;
        while (front.next != null)
            front = front.next;
        front.next = new Node<>(new City(name,x,y),null);
        size++;
    }

    /*根据城市名删除城市信息*/
    public City deleteCityByName(String name) {
        for (Node<City> front = head; front.next != null; front = front.next) {
            City city = front.next.data;
            if (name.equals(city.getName())) {
                front.next = front.next.next;
                size--;
                return city;
            }
        }
        System.out.println("找不到该城市名");
        return null;
    }

    /*根据位置坐标删除城市信息*/
    public City deleteCityByPosition(double x, double y) {
        for (Node<City> front = head; front.next != null; front = front.next) {
            City city = front.next.data;
            if (city.getX() == x && city.getY() == y) {
                front.next = front.next.next;
                size--;
                return city;
            }
        }
        System.out.println("找不到该位置坐标的城市");
        return null;
    }

    /*根据城市名及新的位置坐标来更新城市信息*/
    public City updateCity(String name, double x, double y) {
        for (Node<City> front = head; front.next != null; front = front.next) {
            City city = front.next.data;
            if (name.equals(city.getName())) {
                city.setX(x);
                city.setY(y);
                return city;
            }
        }
        System.out.println("找不到该城市名");
        return null;
    }

    /*获取i处结点的数据*/
    public City get(int i) {
        if (isEmpty() || !checkIndex(i))
            return null;
        Node<City> front = getFrontNode(i);
        return front.next.data;
    }

    /*设置i处结点的数据*/
    public void set(int i, City data) {
        if (!checkIndex(i))
            throw new IndexOutOfBoundsException("index < 0 or index > size-1");
        if (data == null)
            throw new NullPointerException("set data is null");
        Node<City> front = getFrontNode(i);
        front.next.data = data;
    }

    /**
     * 在i处添加结点
     * @param i 添加的位置
     * @param data 添加的数据
     * @return 返回成功添加的结点
     */
    public Node<City> add(int i, City data) {
        if (data == null)
            throw new NullPointerException("add data is null");
        Node<City> front = getFrontNode(i);
        front.next = new Node<>(data, front.next);
        size++;
        return front.next;
    }

    /**
     * 尾添加结点
     * @param data 添加的数据
     * @return 返回成功添加的结点
     */
    public Node<City> add(City data) {
        return add(Integer.MAX_VALUE, data);
    }

    /**
     * 添加数据不重复的结点
     * @param data 要添加的数据
     * @return 返回成功添加的结点
     */
    public Node<City> addDifferent(City data) {
        if (search(data) != null)           //查找到有重复数据的结点，返回null
            return null;
        return add(data);
    }

    /*合并连接*/
    public void addAll(SinglyList list) {
        Node<City> addNode = list.head;
        while (addNode.next != null) {
            addNode = addNode.next;
            this.add(addNode.data);
        }
    }

    /**
     * 删除在i处的结点
     * @param i 删除的位置
     * @return 返回删除掉的结点的数据
     */
    public City remove(int i) {
        if (isEmpty() || !checkIndex(i))
            return null;
        Node<City> front = getFrontNode(i);
        City old = front.next.data;
        front.next = front.next.next;
        size--;
        return old;
    }

    /**
     * 删除第一个数据与key匹配的结点
     * @param key 要删除的结点的数据
     * @return 返回删除掉的结点的数据
     */
    public City remove(City key) {
        for (Node<City> front = head; front.next != null; front = front.next)
            if (front.next.data.equals(key)) {
                City old = front.next.data;
                front.next = front.next.next;
                size--;
                return old;
            }
        return null;
    }

    /*查找返回首个与key相等元素结点，查找不成功返回null*/
    public Node<City> search(City key) {
        for (Node<City> target = head.next; target != null; target = target.next)
            if (target.data.equals(key))
                return target;
        return null;
    }

    /*单链表逆转*/
    public void reverse() {
        Node<City> front = null;
        Node<City> p = this.head.next;
        Node<City> after;
        while (p != null) {
            after = p.next;
            p.next = front;
            front = p;
            p = after;
        }
        head.next = front;
    }

    /*链表长度*/
    public int size() {
        return size;
    }

    /*判断单链表是否为空*/
    public boolean isEmpty() {
        return head.next == null;
    }

    /*判断索引是否在链表长度范围内*/
    private boolean checkIndex(int i) {
        return i >= 0 && i <= size-1;
    }

    /*获取i处的前驱节点*/
    private Node<City> getFrontNode(int i) {
        Node<City> front = head;
        for (int j = 0; front.next != null && j < i; j++)
            front = front.next;
        return front;
    }

    /*清空链表数据*/
    public void clear() {
        head.next = null;
        size = 0;
    }

    /*打印链表所有数据*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName()).append(" {");
        for (Node<City> p = head.next; p != null; p = p.next) {
            sb.append(p.data.toString());
            if (p.next != null)
                sb.append(" , ");
        }
        return sb.append("}").toString();
    }
}
