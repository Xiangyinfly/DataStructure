package com.Queue;

public class arrayQueue {
    private int maxSize;//数组最大容量
    private int front;//队列头，指向队列第一个数据的前一个位置
    private int rear;//队列尾，指向队列的最后一个数据
    private int[] array;

    public arrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        front = - 1;
        rear = - 1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    //数据加入队列
    public void addQueue(int num) {
        if (isFull()) {
            System.out.println("队列已满，不能加入数据...");
            return;
        }
        rear ++;
        array[rear] = num;
    }

    //数据取出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取出数据...");
        }
        front ++;
        return array[front];
    }

    //显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空...");
            return;
        }
        for (int i = 0;i < array.length;i ++) {
            System.out.printf("arr[%d] = %d\n",i,array[i]);
        }
    }

    //显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空...");
        }
        return array[front + 1];
    }
}
