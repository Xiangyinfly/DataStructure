package com.Search;

public class BinarySearch {
    /*
    二分查找
    如果没有该元素，返回-1
     */

    public static int binarySearch(int[] array,int left,int right,int target) {
        int mid = (left + right) / 2;

        if (array[mid] > target) {
            return binarySearch(array,left,mid - 1,target);
        }
        if (array[mid] < target) {
            return binarySearch(array,mid + 1,right,target);
        } else {
            return mid;
        }
    }
}
