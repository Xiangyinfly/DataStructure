package com.Sort;

import com.Search.BinarySearch;

public class test {
    public static void main(String[] args) {
        int[] array = {1,134,23,2323,4,545,2,323,1111,232,43434,323,1212,2,232};
        HeapSort.heapSort(array);
        for (int i :array) {
            System.out.println(i);
        }

    }
}
