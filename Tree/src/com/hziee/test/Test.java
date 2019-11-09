package com.hziee.test;

import com.hziee.node.BinaryNode;
import com.hziee.tree.BinaryTree;

public class Test {
    public static void main(String[] args) {
        //BinaryTree<Integer> tree = new BinaryTree<>(new Integer[] {1,2,4,null,null,5,null,null,3,6,8,null,null,null,7});
        //tree.inOrder();
        //tree.postOrder();
        int[] inOrder = new int[]{4, 2, 5, 1, 8, 6, 3, 7};
        int[] postOrder = new int[]{4, 5, 2, 8, 6, 7, 3, 1};
        BinaryNode<Integer> root = build(inOrder, postOrder);
        BinaryTree<Integer> Tree = new BinaryTree<>(root);
        BinaryTree<Integer> copyTree = new BinaryTree<>(Tree.getRoot());
        copyTree.preOrder();
        System.out.println(copyTree.print());
        copyTree.inOrder();
        System.out.println(copyTree.print());
        copyTree.postOrder();
        System.out.println(copyTree.print());
        /*String str = copyTree.print();
        System.out.println(str);
        System.out.println(str.charAt(0));*/

        /*BinaryTree<Integer> copyTree = new BinaryTree<>(tree.getRoot(), true);
        copyTree.preOrder();
        copyTree.inOrder();
        copyTree.postOrder();*/
        /*System.out.println(tree.size());
        System.out.println(tree.n0);
        System.out.println(tree.n1);
        System.out.println(tree.n2);
        System.out.println(tree.n0 == tree.n2 + 1);*/
    }

    private static double average(BinaryTree<Integer> tree) {
        int size = tree.size();
        return 1.0 * tree.sum / size;
    }

    private static BinaryNode<Integer> build(int[] inOrder, int[] postOrder) {
        int inLen = inOrder.length;
        int postLen = postOrder.length;
        // 特判
        if (inLen != postLen) {
            throw new RuntimeException("输入错误");
        }
        return build(inOrder, 0, inLen - 1, postOrder, 0, postLen - 1);
    }

    /**
     * 使用中序遍历序列 inOrder 的子区间 [inLeft, inRight]
     * 与后序遍历序列 postOrder 的子区间 [postLeft, postRight] 构建二叉树
     *
     * @param inOrder   中序遍历序列
     * @param inLeft    中序遍历序列的左边界
     * @param inRight   中序遍历序列的右边界
     * @param postOrder 后序遍历序列
     * @param postLeft  后序遍历序列的左边界
     * @param postRight 后序遍历序列的右边界
     * @return 二叉树的根结点
     */
    private static BinaryNode<Integer> build(int[] inOrder, int inLeft, int inRight,
                                      int[] postOrder, int postLeft, int postRight) {
        if (inLeft > inRight || postLeft > postRight) {
            return null;
        }

        int pivot = postOrder[postRight];
        int pivotIndex = inLeft;

        // 注意这里如果编写不当，有数组下标越界的风险
        while (inOrder[pivotIndex] != pivot) {
            pivotIndex++;
        }
        BinaryNode<Integer> root = new BinaryNode<>(pivot);
        root.left = build(inOrder, inLeft, pivotIndex - 1,
                postOrder, postLeft, postRight - inRight + pivotIndex - 1);

        root.right = build(inOrder, pivotIndex + 1, inRight,
                postOrder, postRight - inRight + pivotIndex, postRight - 1);
        return root;
    }
}
