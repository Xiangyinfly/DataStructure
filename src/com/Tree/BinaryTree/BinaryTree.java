package com.Tree.BinaryTree;

public class BinaryTree {
    private Node root;

    //前序遍历，其他两种类似
    //重载一下
    public void preOrder() {
        preOrder(root);
    }
    public void preOrder(Node node) {
        System.out.println(node);
        if(node.getLeft_next() != null) {
            preOrder(node.getLeft_next());
        }
        if(node.getRight_next() != null) {
            preOrder(node.getRight_next());
        }
    }

    //前序遍历查找
    public Node preOrderSearch(int num) {
        return preOrderSearch(root, num);
    }
    public Node preOrderSearch(Node node,int num) {
        Node res = null;
        if(node.getNum() == num) {
            return node;
        }
        if(node.getLeft_next() != null) {
            res = preOrderSearch(node.getLeft_next(), num);
        }
        if(res != null) {
            return res;
        }
        if(node.getRight_next() != null) {
            res = preOrderSearch(node.getRight_next(), num);
        }
        return res;
    }

    //删除节点
    //规定：删除叶子结点或删除非叶子结点的的子树
    public void delete1(int num) {
        delete1(root, num);
    }
    public void delete1(Node node,int num) {
        if(root != null) {
            if(num == root.getNum()) {
                root = null;
            }
            if(node.getLeft_next() != null && node.getLeft_next().getNum() == num) {
                node.setLeft_next(null);
                return;
            }
            if(node.getRight_next() != null && node.getRight_next().getNum() == num) {
                node.setRight_next(null);
                return;
            }
            if(node.getLeft_next() != null) {
                delete1(node.getLeft_next(), num);
            }
            if(node.getRight_next() != null) {
                delete1(node.getRight_next(), num);
            }
        } else {
            System.out.println("tree is empty");
        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}

//class Binarytree里面的功能可以封装在class Node里，用this代指是谁调用的方法实现递归

