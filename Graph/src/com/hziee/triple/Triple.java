package com.hziee.triple;

public class Triple implements Comparable<Triple> {
    public int row, column, value;

    public Triple(int row, int column, int value) {
        if (row >= 0 && column >= 0) {
            this.row = row;
            this.column = column;
            this.value = value;
        } else {
            throw new IllegalArgumentException("行、列号不能为负数：row=" + row + "，column=" + column);
        }
    }

    public Triple(Triple triple) {
        this(triple.row, triple.column, triple.value);
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + "," + value + ")";
    }

    @Override
    public int compareTo(Triple triple) {
        if (this.row == triple.row && this.column == triple.column) {
            return 0;
        }
        return (this.row < triple.row || this.row == triple.row && this.column < triple.column) ? -1 : 1;
    }
}
