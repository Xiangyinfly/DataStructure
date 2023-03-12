package com.LinkedList;

public class test {
    public static void main(String[] args) {

        Node node1 = new Node(1,"a");
        Node node2 = new Node(2,"a");
        Node node3 = new Node(3,"a");
        Node node4 = new Node(4,"a");
        Node node5 = new Node(5,"a");
        Node node6 = new Node(6,"a");

        singleLinkedList singleLinkedList1 = new singleLinkedList();
        singleLinkedList singleLinkedList2 = new singleLinkedList();

        singleLinkedList1.add(node1);
        singleLinkedList1.add(node3);
        singleLinkedList1.add(node5);
        singleLinkedList2.add(node2);
        singleLinkedList2.add(node4);
        singleLinkedList2.add(node6);

        singleLinkedList2.showList();
        System.out.println("================");
        singleLinkedList1.showList();
        System.out.println("================");

        singleLinkedList.combineList(singleLinkedList2.head,singleLinkedList1.head);

        singleLinkedList2.showList();
        System.out.println("================");
        singleLinkedList1.showList();

    }
}
