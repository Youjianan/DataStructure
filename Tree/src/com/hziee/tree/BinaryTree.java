package com.hziee.tree;

import com.hziee.node.BinaryNode;

public class BinaryTree<T> {
    private BinaryNode<T> root;
    public int n0 = 0;
    public int n1 = 0;
    public int n2 = 0;
    public Integer sum = 0;
    private int i = 0;
    private StringBuilder sb = new StringBuilder();

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(BinaryNode<T> root) {
        this.root = cloneTree(root);
    }

    /*preList数组指定二叉树标明空子树的先根遍历序列*/
    public BinaryTree(T[] preList) {
        this.root = create(preList);
    }

    private BinaryNode<T> create(T[] preList) {
        BinaryNode<T> node = null;
        if (i < preList.length) {
            T elem = preList[i];
            i++;
            if (elem != null) {
                node = new BinaryNode<>(elem);
                node.left = create(preList);
                node.right = create(preList);
            }
        }
        return node;
    }

    public BinaryNode<T> cloneTree(BinaryNode<T> srcRoot) {
        BinaryNode<T> destRoot = null;
        if (srcRoot == null)
            return null;
        destRoot = new BinaryNode<>(srcRoot.data);
        destRoot.left = cloneTree(srcRoot.left);
        destRoot.right = cloneTree(srcRoot.right);
        return destRoot;
    }

    public BinaryNode<T> getRoot() {
        return this.root;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        this.n0 = 0;
        this.n1 = 0;
        this.n2 = 0;
        this.sum = 0;
        return size(this.root);
    }

    private int size(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        if (node.data instanceof Integer) {
            this.sum += (Integer) node.data;
        }
        int n = 0;
        if (node.left != null) n++;
        if (node.right != null) n++;
        if (n == 0) this.n0++;
        if (n == 1) this.n1++;
        if (n == 2) this.n2++;
        return 1 + size(node.left) + size(node.right);
    }

    public boolean equals(BinaryTree<T> tree) {

        return true;
    }

    public BinaryNode<T> insert(BinaryNode<T> parent, T data, boolean leftChild) {
        if (data == null) {
            return null;
        }
        if (leftChild) {
            return parent.left = new BinaryNode<>(data, parent.left, null);
        }
        return parent.right = new BinaryNode<>(data, null, parent.right);
    }

    /*插入data作为根结点*/
    public BinaryNode<T> insert(T data) {
        return this.root = new BinaryNode<>(data, this.root, null);
    }

    /*删除子树*/
    public void remove(BinaryNode<T> parent, boolean leftChild) {
        if (leftChild) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }

    public void clear() {
        this.root = null;
    }

    /*输出先根次序*/
    public void preOrder() {
        sb.delete(0, sb.length());
        preOrder(this.root);
    }

    /*先根次序递归实现*/
    private void preOrder(BinaryNode<T> node) {
        if (node != null) {
            sb.append(node.data.toString()).append(" ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /*输出中根次序*/
    public void inOrder() {
        sb.delete(0, sb.length());
        inOrder(this.root);
    }

    /*中根次序递归实现*/
    private void inOrder(BinaryNode<T> node) {
        if (node != null) {
            inOrder(node.left);
            sb.append(node.data.toString()).append(" ");
            inOrder(node.right);
        }
    }

    /*输出后根次序*/
    public void postOrder() {
        sb.delete(0, sb.length());
        postOrder(this.root);
    }

    /*后根次序递归实现*/
    private void postOrder(BinaryNode<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            sb.append(node.data.toString()).append(" ");
        }
    }

    public void exchange() {
        exchange(this.root);
    }

    /*将每个结点左子树和右子树进行交换*/
    private void exchange(BinaryNode<T> node) {
        if (node == null || node.isLeaf()) {
            return;
        }
        BinaryNode<T> temp = node.left;
        node.left = node.right;
        node.right = temp;
        exchange(node.left);
        exchange(node.right);
    }

    public String print() {
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(this.root);
    }

    /*返回先根次序遍历以node为根的子树描述串*/
    private String toString(BinaryNode<T> node) {
        if (node == null)
            return "";
        return node.data.toString() + toString(node.left) + toString(node.right);
    }

    /*public int height() {

    }*/
}
