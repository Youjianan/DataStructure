package com.hziee.graph;

import com.hziee.list.SeqList;
import com.hziee.list.SinglyList;

public abstract class AbstractGraph<T> {
    protected static final int MAX_WEIGHT = 0x0000ffff;
    protected SeqList<T> vertexlist;

    public AbstractGraph(int length) {
        this.vertexlist = new SeqList<T>(length);
    }

    public AbstractGraph() {
        this(10);
    }

    public int vertexCount() {
        return this.vertexlist.size();
    }

    @Override
    public String toString() {
        return "顶点集合：" + this.vertexlist.toString() + "\n";
    }

    public T getVertex(int i) {
        return this.vertexlist.get(i);
    }

    public void setVertex(int i, T x) {
        this.vertexlist.set(i, x);
    }

    public abstract int weight(int i, int j);

    public void shortestPath(int a, int b) {
        int n = this.vertexCount();
        Matrix path = new Matrix(n);
        Matrix dist = new Matrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int w = this.weight(i, j);
                dist.set(i, j, w);
                path.set(i, j, (i != j && w < MAX_WEIGHT ? i : -1));
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (i != k) {
                    for (int j = 0; j < n; j++) {
                        if (j != k && j != i && dist.get(i, j) > dist.get(i, k) + dist.get(k, j)) {
                            dist.set(i, j, dist.get(i, k) + dist.get(k, j));
                            path.set(i, j, path.get(k, j));
                        }
                    }
                }
            }
        }
        System.out.println("最短路线: " + toPath(path, a, b) + "\n路线长度: " + (dist.get(a, b) == MAX_WEIGHT ? "∞" : dist.get(a, b)));
    }

    public String toPath(Matrix path, int i, int j) {
        SinglyList<T> pathlink = new SinglyList<>();
        pathlink.insert(0, this.getVertex(j));
        for (int k = path.get(i, j); k != j && k != i && k != -1; k = path.get(i, k)) {
            pathlink.insert(0, this.getVertex(k));
        }
        pathlink.insert(0, this.getVertex(i));
        return pathlink.toString();
    }
}
