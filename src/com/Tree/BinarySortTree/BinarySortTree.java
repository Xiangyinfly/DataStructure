package com.Tree.BinarySortTree;

public class BinarySortTree {
    /**
     * 二叉排序树
     */
    Node root;

    //添加结点
    public void add(Node node) {
        if (root == null) {
            root = node;
            return;
        }
        add(node,root);
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("BST is empty");
            return;
        }
        infixOrder(root);
    }

    //删除结点
    public void delete(int value) {
        if (root == null) {
            return;
        }

        Node target = searchTarget(value);
        if (target == null) {
            return;
        }
        //如果该结点没有父节点
        if (target == root) {
            root = null;
            return;
        }
        Node parent = searchTargetParent(value);

        if (target.left == null && target.right == null) {
            //如果删除的结点是叶子结点
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
            }
            if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (target.left != null && target.right != null) {
            //如果删除的结点有两棵子树
            Node point = target.right;
            //找到右子树的最小结点
            while (point.left != null) {
                point = point.left;
            }
            target.value = point.value;
            delete(point.value);
        } else {
            //如果删除的结点有一棵子树
            if (target.left != null) {
                if (parent.left.value == value) {
                    parent.left = target.left;
                } else {
                    parent.right = target.left;
                }
            } else {
                if (parent.left.value == value) {
                    parent.left = target.right;
                } else {
                    parent.right = target.right;
                }
            }
        }
    }


    //=================================辅助方法=================================

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

    //查找要删除的结点
    public Node searchTarget(int value) {
        return searchTarget(value,root);
    }
    public Node searchTarget(int value, Node node) {
        if (value == node.value) {
            return node;
        }
        if (value < node.value) {
            return node.left != null ? searchTarget(value,node.left) : null;
        }
        return node.right != null ? searchTarget(value,node.right) : null;
    }

    //查找要删除节点树父节点
    public Node searchTargetParent(int value) {
        return searchTargetParent(value,root);
    }
    public Node searchTargetParent(int value,Node node) {
        if ((node.left != null && node.left.value == value)
                || (node.right != null && node.right.value == value)) {
            return node;
        }
        if (node.value > value && node.left != null) {
            return searchTargetParent(value,node.left);
        }
        if (node.value <= value && node.right != null) {
            return searchTargetParent(value,node.right);
        }
        return null;
    }
}
