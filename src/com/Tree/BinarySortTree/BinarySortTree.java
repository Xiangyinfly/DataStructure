package com.Tree.BinarySortTree;

public class BinarySortTree {
    /**
     * 二叉排序树
     */
    Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        add(node,root);
    }
    public void add(Node node,Node point) {
        if (node == null) {
            return;
        }
        if (node.value < point.value) {
            if (point.left == null) {
                point.left = node;
            } else {
                add(node,point.left);
            }
        } else {
            if (point.right == null) {
                point.right = node;
            } else {
                add(node,point.right);
            }
        }
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
