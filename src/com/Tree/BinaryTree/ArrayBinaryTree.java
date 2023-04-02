package com.Tree.BinaryTree;

/**
 * 顺序存储二叉树的前序遍历
 * 占用或浪费空间比较大，一般用于储存完全二叉树
 */
public class ArrayBinaryTree {
    int[] array;
    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    //重写一下，用于全部遍历
    public void preOrder() {
        preOrder(0);
    }
    public void preOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("array is empty");
            return;
        }
        System.out.println(array[index]);
        //向左遍历子节点
        if (index * 2 + 1 < array.length) {
            preOrder(index * 2 + 1);
        }
        //向右遍历子节点
        if (index * 2 + 2 < array.length) {
            preOrder(index * 2 + 2);
        }
    }
}
