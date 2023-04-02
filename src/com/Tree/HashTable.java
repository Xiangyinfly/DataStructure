package com.Tree;

public class HashTable {
    public static void main(String[] args) {

    }
}

class empHashTable {
    private empLinkedList[] empLinkedLists;
    private int size;

    public empHashTable(int size) {
        empLinkedLists = new empLinkedList[size];
        for (empLinkedList empLinkedList :empLinkedLists) {
            empLinkedList = new empLinkedList();
        }

        this.size = size;
    }

    public void add(Emp emp) {
        int listNum = hashingFunction(emp.getId());
        empLinkedLists[listNum].add(emp);
    }

    public void show() {
        for (empLinkedList empLinkedList :empLinkedLists) {
            empLinkedList.show();
        }

    }

    public int hashingFunction(int id) {
        return id % size;
    }
}

class empLinkedList {
    private Emp head;

    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return;
        }
        Emp point = head;
        while (point.getNext() != null) {
            point = point.getNext();
        }
        point.setNext(emp);
    }

    public void show() {
        if (head == null) {
            System.out.println("list is empty");
            return;
        }
        Emp point = head;
        while (point != null) {
            System.out.println(point.toString());
            point = point.getNext();
        }
    }
}

class Emp {
    private int id;
    private String name;
    private Emp next;

    public Emp(int id, String name, Emp next) {
        this.id = id;
        this.name = name;
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp getNext() {
        return next;
    }

    public void setNext(Emp next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
