package com.Sort;

public class ShellSort {
    /**
     * 希尔排序
     * 最好情况：时间复杂度 O(N * log^2N)
     * 最坏情况：时间复杂度 O(N ^ 2)，增量元素不互质，则小增量可能根本不起作用
     * 稳定性：不稳定。
     */

    /*
    //交换式
    我一开始错误的方法：
    public static void shellSort1(int[] array) {
        int temp = 0;
        int gap = array.length / 2;

        while (gap > 0) {
            for (int i = 0; i < gap; i++) {
                for (int j = i; j + gap < array.length; j += gap) {
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }

            gap /= 2;
        }
    }
    */

    //交换式
    public static void shellSort1(int[] array) {
        int temp = 0;
        int gap = array.length / 2;

        while (gap > 0) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) {
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    } else {
                        break; // 如果已经找到正确的位置，就不需要继续往前比较了
                    }
                }
            }

            gap /= 2;
        }
    }

    //位移式
    public static void shellSort2(int[] array) {
        int temp = 0;
        int gap = array.length / 2;

        while (gap > 0) {
            //此处对每一组进行插入排序
            for (int i = gap; i < array.length; i++) {
                int insertValue = array[i];
                int insertIndex = i;

                while (insertIndex >= gap && array[insertIndex - gap] > insertValue) {
                    array[insertIndex] = array[insertIndex - gap];
                    insertIndex -= gap;
                }
                array[insertIndex] = insertValue;
            }

            gap /= 2;
        }
    }
}
