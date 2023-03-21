package com.Sort;

public class test {
    public static void main(String[] args) {
        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 4;
        arr[2] = 3;
        arr[3] = 9;
        arr[4] = 2;

        BubbleSort.bubbleSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }

        SelectSort.selectSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }

        InsertSort.insertSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
