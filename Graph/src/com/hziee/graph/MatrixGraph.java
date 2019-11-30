package com.hziee.graph;

import com.hziee.triple.Triple;

public class MatrixGraph<T> extends AbstractGraph<T> {
    protected Matrix matrix;

    public MatrixGraph(int length) {
        super(length);
        this.matrix = new Matrix(length);
    }

    public MatrixGraph() {
        this(10);
    }

    public MatrixGraph(T[] vertices) {
        this(vertices.length);
        for (T vertex : vertices) {
            this.insertVertex(vertex);
        }
    }

    public MatrixGraph(T[] vertices, Triple[] edges) {
        this(vertices);
        for (int j = 0; j < edges.length; j++) {
            this.insertEdge(edges[j]);
        }
    }

    @Override
    public String toString() {
        String str = super.toString() + "邻接矩阵：\n";
        int n = this.vertexCount();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.matrix.get(i, j) == MAX_WEIGHT) {
                    str += "    ∞";
                } else {
                    str += String.format("%6d", this.matrix.get(i, j));
                }
            }
            str += "\n";
        }
        return str;
    }

    public void insertEdge(int i, int j, int weight) {
        if (i != j) {
            if (weight <= 0 || weight > MAX_WEIGHT) {
                weight = MAX_WEIGHT;
            }
            this.matrix.set(i, j, weight);
        } else {
            throw new IllegalArgumentException("不能插入自身环，i=" + i + ",j=" + j);
        }
    }

    public void insertEdge(Triple edge) {
        this.insertEdge(edge.row, edge.column, edge.value);
    }

    public int insertVertex(T x) {
        int i = this.vertexlist.insert(x);
        if (i >= this.matrix.getRows()) {
            this.matrix.setRowsColumns(i + 1, i + 1);
        }
        for (int j = 0; j < i; j++) {
            this.matrix.set(i, j, MAX_WEIGHT);
            this.matrix.set(j, i, MAX_WEIGHT);
        }
        return i;
    }

    @Override
    public int weight(int i, int j) {
        return this.matrix.get(i, j);
    }
}
