package com.LinkedList;

public class circleLinkedList {
    //利用环形链表解决约瑟夫问题
    private Child first = new Child(-1);

    //创建环形列表
    public void establish(int length) {
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
