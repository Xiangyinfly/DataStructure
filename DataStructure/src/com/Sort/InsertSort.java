package com.Sort;

public class InsertSort {
    /**
     * 插入排序
     * 时间复杂度为O(n^2)
     */

    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertValue = array[i];//将要插入的值，先保存到insertValue
            int insertIndex = i - 1;//将要插入值应该插入位置的索引

            //🌟数组中实现insertValue的插入
            while (insertIndex >= 0 && insertValue < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex --;//满足insertValue < array[insertIndex]情况下向前推进
            }
            array[insertIndex + 1] = insertValue;
        }
    }
}
