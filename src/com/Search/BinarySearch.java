package com.Search;

import java.util.ArrayList;

public class BinarySearch {
    /*
    二分查找
    如果没有该元素，返回-1
     */

    public static ArrayList<Integer> binarySearch(int[] array, int left, int right, int target) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (left > right) {
            return arrayList;
        }

        int mid = (left + right) / 2;

        if (array[mid] > target) {
            return binarySearch(array,left,mid - 1,target);
        }
        if (array[mid] < target) {
            return binarySearch(array,mid + 1,right,target);
        } else {
            int temp = mid - 1;
            while (temp >= 0 && array[temp] == target) {
                arrayList.add(temp);
                temp --;
            }
            arrayList.add(mid);
            temp = mid + 1;
            while (temp < array.length && array[temp] == target) {
                arrayList.add(temp);
                temp ++;
            }
            return arrayList;
        }
    }
}
