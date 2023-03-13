package com.LinkedList;


public class doubleLinkedList {
    public Node_double head = new Node_double(0, "");

    public doubleLinkedList(Node_double head) {
        this.head = head;
    }

    //遍历链表
    public void showList() {
        if (head.next == null) {
            System.out.println("链表为空...");
            return;
        }
        Node_double point = head.next;
        while (point != null) {
            System.out.println(point);
            point = point.next;
        }
    }

    public void add(Node_double nodeDouble) {
        Node_double point = head;
        while (point.next != null) {
            point = point.next;
        }
        point.next = nodeDouble;
        nodeDouble.pre = point;
    }

    public void delete(int num) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Node_double temp = head;
        while (temp != null) {
            if (temp.num == num) {
                temp.pre.next = temp.next;
                if (temp.next != null) {//防止删除最后一个节点时出现空指针异常
                    temp.next.pre = temp.pre;
                }
                return;
            }
            temp = temp.next;
        }
        System.out.println("没有此节点");
    }
}

class Node_double {
    public int num;
    public String attribute;
    public Node_double pre;
    public Node_double next;

    public Node_double(int num, String attribute) {
        this.num = num;
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "Node{" +
                "node=" + num +
                ", name='" + attribute + '\'' +
                '}';
    }
}