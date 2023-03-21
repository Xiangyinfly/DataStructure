package com.Sort;

public class BubbleSort {
    /**
     * 冒泡排序
     * 时间复杂度为O(n^2)
     */

    public static void bubbleSort(int[] array) {
        int temp = 0;
        //优化：引入flag，当排序中有一轮未发生交换时，排序已经结束
        boolean flag = false;

        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - j - 1; i++) {
                if (array[i] > array[i+1]) {
                    //如果一轮中有交换，则将flag置为true
                    flag = true;
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                }
            }
            if (!flag) {//如果一轮中有没有交换，flag保持false，说明冒泡排序完成，此时break
                break;
            }
            flag = false;//每次内循环之后将flag置为false，以进行下一轮的判断
        }
    }
}
