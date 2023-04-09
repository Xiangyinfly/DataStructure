package com.Tree.BinaryTree;

public class ThreadedBinaryTree {
    private Node root;
    private Node pre;

    //================================中序线索化二叉树及其遍历================================

    //重载一下threadNode方法
    public void infixThreadNode() {
        infixThreadNode(root);
    }

    /*
    该方法实现线索化二叉树的中序遍历
    原理：有线索先按线索找（注意线索上的节点是需要访问的），等到线索断了就往下找右孩子节点。
     */
    public void infixOrderList() {
        Node point = root;
        while (point != null) {
            //找到最左侧的最后一个节点
            while (point.getLeftType() == 0) {
                point = point.getLeft_next();
            }
            //输出该节点
            System.out.println(point);
            //如果有线索，则继续沿线索输出，直到线索断了
            while (point.getRightType() == 1) {
                point = point.getRight_next();
                System.out.println(point);
            }
            //线索断了之后，指针向右侧移动，结合20行继续寻找次左侧的最后一个节点
            point = point.getRight_next();
        }
    }

    /**
     * 中序线索化二叉树
     * @param node 当前要线索化的节点
     */
    public void infixThreadNode(Node node) {
        if (node == null) {
            return;
        }

        //线索化左子树
        infixThreadNode(node.getLeft_next());

        //线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft_next() == null) {
            node.setLeft_next(pre);
            node.setLeftType(1);
        }
        //处理当前节点的后继节点
        if (pre != null && pre.getRight_next() == null) {
            pre.setRight_next(node);
            pre.setRightType(1);
        }
        pre = node;

        //线索化右子树
        infixThreadNode(node.getRight_next());
    }

    //================================前序线索化二叉树及其遍历================================
    public void preThreadNode(Node node) {
        if (node == null) {
            return;
        }

        if (node.getLeft_next() == null) {
            node.setLeft_next(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight_next() == null) {
            pre.setRight_next(node);
            pre.setRightType(1);
        }
        pre = node;

        if (node.getLeftType() == 0) {//为什么这里要判断而中序不需要判断？
            preThreadNode(node.getLeft_next());
        }
        if (node.getRightType() == 0) {
            preThreadNode(node.getRight_next());
        }
    }

    public void preOrderList() {
        Node point = root;
        while (point != null) {
            while (point.getLeftType() == 0) {
                System.out.println(point);//输出当前节点
                point = point.getLeft_next();//如果有左子树，先输出左节点
            }
            System.out.println(point);
            point = point.getRight_next();//没有左子树后就沿线索向右遍历输出
        }
    }

    //================================后序线索化二叉树及其遍历================================
    private void postThreadedNodes(Node node) {
        if (node == null) {
            return;
        }

        //线索化左子树
        infixThreadNode(node.getLeft_next());

        //线索化右子树
        infixThreadNode(node.getRight_next());

        //线索化当前节点
        //处理当前节点的前驱节点
        if (node.getLeft_next() == null) {
            node.setLeft_next(pre);
            node.setLeftType(1);
        }
        //处理当前节点的后继节点
        if (pre != null && pre.getRight_next() == null) {
            pre.setRight_next(node);
            pre.setRightType(1);
        }
        pre = node;
    }

    public void postThreadedList() {
        Node point = root;
        while (point != null) {
            while (point.getLeftType() == 0) {
                point = point.getLeft_next();
            }
            while (point != null && point.getRightType() == 1) {
                System.out.println(point);
                pre = point;
                point = point.getRight_next();
            }
            //若point结点为根节点，则遍历完成
            if (point == root) {
                System.out.println(point);
                return;
            }
            //若pot.getRight_next() == pre则说明以pot为根结点的子树遍历完成，应遍历pot兄弟结点。
            //先获取pot结点的父节点，再获取pot结点的兄弟结点。
            //若pot结点无兄弟结点，则继续向上寻找。
            while (point != null && point.getRight_next() == pre) {
                System.out.println(point);
                pre = point;
                point = point.getParent();
            }
            if (point != null && point.getRightType() == 0) {
                point = point.getRight_next();
            }
        }
    }
}
