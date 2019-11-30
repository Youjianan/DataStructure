package com.hziee.list;

public class SeqList<T> {
    private Object[] element;
    private int n;              //元素个数
    public SeqList(int length) {
        this.element = new Object[length];
        this.n = 0;
    }

    public SeqList() {
        this(64);
    }

    public SeqList(T[] values) {
        this(values.length);
        System.arraycopy(values, 0, this.element, 0, values.length);
        this.n = element.length;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public int size() {
        return this.n;
    }

    public T get(int i) {
        if (i >= 0 && i < this.n)
            return (T) this.element[i];
        return null;
    }

    public void set(int i, T x) {
        if (x == null)
            throw new NullPointerException("x==null");
        if (i >= 0 && i < this.n)
            this.element[i] = x;
        else throw new IndexOutOfBoundsException(i + "");
    }

    public int insert(int i, T x) {
        if (x == null)
            throw new NullPointerException("x==null");
        if (i < 0)
            i = 0;
        if (i > this.n)
            i = this.n;
        Object[] source = this.element;
        if (this.n == element.length) {
            this.element = new Object[source.length * 2];
            System.arraycopy(source, 0, this.element, 0, i);
        }
        if (this.n - i >= 0)
            System.arraycopy(source, i, this.element, i + 1, this.n - i);
        this.element[i] = x;
        this.n++;
        return i;
    }

    public int insert(T x) {
        return this.insert(this.n, x);
    }

    public T remove(int i) {
        if (this.n > 0 && i >= 0 && i < this.n) {
            T old = (T) this.element[i];
            if (this.n - 1 - i >= 0)
                System.arraycopy(this.element, i + 1, this.element, i, this.n - 1 - i);
            this.element[this.n-1] = null;
            this.n--;
            return old;
        }
        return null;
    }

    public void clear() {
        this.n = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.n > 0)
            sb.append(this.element[0].toString());
        for (int i = 1; i < this.n; i++) {
            sb.append(" ").append(this.element[i].toString());
        }
        return sb.toString();
    }
}
