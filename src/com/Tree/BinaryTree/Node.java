package com.Tree.BinaryTree;

public class Node {
    private int num;
    private String name;
    private Node left_next;
    private Node right_next;

    public Node(int num, String name) {
        this.num = num;
        this.name = name;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Node getLeft_next() {
        return left_next;
    }
    public void setLeft_next(Node left_next) {
        this.left_next = left_next;
    }
    public Node getRight_next() {
        return right_next;
    }
    public void setRight_next(Node right_next) {
        this.right_next = right_next;
    }
    @Override
    public String toString() {
        return "Node [num=" + num + ", name=" + name + "]";
    }

//=========================================================================================
    /**
     * 此处定义用于线索化二叉树
     * leftType == 0 -> 指向左子树
     * leftType == 1 -> 指向前驱节点
     * rightType == 0 -> 指向右子树
     * rightType == 1 -> 指向后继节点
     */
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}
