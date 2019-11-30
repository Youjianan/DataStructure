package com.hziee.graph;

public class Matrix {
    private int rows;
    private int columns;
    private int[][] element;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.element = new int[rows][columns];
    }

    public Matrix(int n) {
        this(n, n);
    }

    public Matrix(int rows, int columns, int[][] element) {
        this(rows, columns);
        for (int i = 0; i < element.length && i < rows; i++) {
            for (int j = 0; j < element[i].length && j < columns; j++) {
                this.element[i][j] = element[i][j];
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int get(int i, int j) {
        if (i >= 0 && i < this.rows && j >= 0 && j < this.columns) {
            return this.element[i][j];
        }
        throw  new IndexOutOfBoundsException("Out Of Bounds");
    }

    public void set(int i,int j,int x){
        if (i >= 0 && i < this.rows && j >= 0 && j < this.columns) {
            this.element[i][j] = x;
        }else{
            throw new IndexOutOfBoundsException("Out Of Bounds");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getName()+"("+this.rows+","+this.columns+"):\n");
        for(int i = 0; i<rows;i++){
            for(int j = 0;j<columns;j++){
                sb.append(String.format("%6d",this.element[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void setRowsColumns(int rows, int columns){
        if(rows>0 && columns >0){
            if(rows>this.element.length || columns >this.element[0].length){
                int[][] source = element;
                this.element = new int[rows][columns];
                for(int i = 0;i<this.rows;i++){
                    for(int j = 0;j<this.columns;j++){
                        this.element[i][j] = source[i][j];
                    }
                }
            }
            this.rows = rows;
            this.columns = columns;
        }else{
            throw new IllegalArgumentException("error");
        }
    }
}
