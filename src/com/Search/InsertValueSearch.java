package com.Search;

import java.util.ArrayList;

public class InsertValueSearch {
    /*
    插值查找
     */
    public static ArrayList<Integer> insertValueSearch(int[] array, int left, int right, int target) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (left > right || target > array[array.length - 1] || target < array[0]) {
            return arrayList;
        }

        int mid = left + (right - left) * (target - array[left]) / (array[right] - array[left]);

        if (array[mid] > target) {
            return insertValueSearch(array,left,mid - 1,target);
        }
        if (array[mid] < target) {
            return insertValueSearch(array,mid + 1,right,target);
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
