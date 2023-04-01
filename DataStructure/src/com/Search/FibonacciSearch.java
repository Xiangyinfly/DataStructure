package com.Search;

import java.util.Arrays;

public class FibonacciSearch {
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; // 斐波那契分割数值的下标
        int mid = 0; // 存放mid值
        int[] f = fibonacci(); // 获取斐波那契数列

        // 获取斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }

        // 将原数组扩展至f[k]-1的长度
        int[] temp = Arrays.copyOf(arr, f[k] - 1);

        // 对扩展后的数组剩余部分赋值为原数组的最后一个值
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = arr[arr.length - 1];
        }

        // 开始查找
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }

        return -1;
    }

    private static int[] fibonacci() {
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;

        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;
    }

}
