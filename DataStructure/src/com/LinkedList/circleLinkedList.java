package com.LinkedList;

public class circleLinkedList {
    //利用环形链表解决约瑟夫问题
    private Child first = new Child(-1);

    //创建环形列表
    public void add(int length) {
        if (length < 1) {
            System.out.println("length error...");
            return;
        }
        Child point = null;
        for (int i = 1;i <= length;i ++) {
            Child child = new Child(i);
            if (i == 1) {
                first = child;
                first.setNext(first);//构成环状
                point = first;
            } else {
                point.setNext(child);
                child.setNext(first);
                point = child;
            }
        }
    }

    //遍历环形链表
    public void show() {
        if (first == null) {
            System.out.println("empty list...");
            return;
        }
        Child point = first;
        while (point.getNext() != first) {
            System.out.println(point);
            point = point.getNext();
        }
    }

    //约瑟夫问题解法
    public void childSort(int length,int startNum,int intervalNum) {
        add(length);
        if (first == null || startNum < 1 || startNum > length) {
            System.out.println("error");
        }
        Child point = first;
        //先使point指向环形末尾
        while (point.getNext() != first) {
            point = point.getNext();
        }
        //将point和first向前移动k - 1个节点
        for (int i = 1;i <= startNum - 1;i ++) {
            point = point.getNext();
            first = first.getNext();
        }

        while (true) {
            for (int i = 1;i <= intervalNum;i ++) {
                point = point.getNext();
                first = first.getNext();
                System.out.println(first);
                first = first.getNext();
                point.setNext(first);
            }
            if (first == point) {
                System.out.println(first);
                break;
            }
        }
    }
}

class Child {
    private int num;
    private Child next;

    public Child(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Child getNext() {
        return next;
    }

    public void setNext(Child next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Child{" +
                "num=" + num +
                '}';
    }
}
