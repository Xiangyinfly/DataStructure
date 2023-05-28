package com.Tree.AVLTree;

public class AVLTree {
    Node root;
    private static final int MAX_HEIGHT_DIFF = 1;

    //插入节点
    public void insert(int value) {
        root = insert(root,value);
    }
    public Node insert(Node point,int value) {
        if (point == null) {
            return new Node(value);
        }
        if (value < point.value) {
            point.left = insert(point.left,value);
        }
        if (value > point.value){
            point.right = insert(point.right,value);
        }
        if (value == point.value) {
            System.out.println("repeated value!");
        }
        return balance(point);
    }
    //删除节点



    //平衡二叉树
    public Node balance(Node node) {
        if (Math.abs(getLeftHeight(node) - getRightHeight(node)) <= MAX_HEIGHT_DIFF) {
            //左右子树高度差小于1，不旋转
            return node;
        }
        if (getLeftHeight(node) - getRightHeight(node) > MAX_HEIGHT_DIFF) {
            //左子树比右子树高度差大于1
            if (getLeftHeight(node.left) >= getRightHeight(node.left)) {
                //左子树的左子树比左子树的右子树高，右旋
                node = rightRotate(node);
            } else {
                //左子树的左子树比左子树右子树低，先左旋在右旋
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }
        }
        if (getRightHeight(node) - getLeftHeight(node) > MAX_HEIGHT_DIFF) {
            //右子树比左子树高度差大于1
            if (getRightHeight(node.right) >= getLeftHeight(node.right)) {
                //右子树的右子树比右子树的左子树高，左旋
                node = leftRotate(node);
            } else {
                //右子树的右子树比右子树的左子树低，先右旋在左旋
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        }

        return node;
    }

    //左旋
    public Node leftRotate(Node node) {
        Node point = node.right;
        node.right = point.left;
        point.left = node;
        return point;
    }
    //右旋
    public Node rightRotate(Node node) {
        Node point = node.left;
        node.left = point.right;
        point.right = node;
        return point;
    }


    //返回左子树的高度
    public int getLeftHeight(Node node) {
        if (node.left == null) {
            return 0;
        }
        return getHeight(node.left);
    }
    //返回右子树的高度
    public int getRightHeight(Node node) {
        if (node.right == null) {
            return 0;
        }
        return getHeight(node.right);
    }
    //返回当前节点的高度，即以该节点为根节点的树的高度
    public int getHeight(Node node) {
        return Math.max(node.left == null ? 0 : getHeight(node.left),node.right == null ? 0 : getHeight(node.right)) + 1;
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("BST is empty");
            return;
        }
        infixOrder(root);
    }
    public void infixOrder(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            infixOrder(node.left);
        }
        System.out.println(node);
        if (node.right != null) {
            infixOrder(node.right);
        }
    }
}
