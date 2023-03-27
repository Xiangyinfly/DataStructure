package com.Sort;

public class RadixSort {
    /**
     * 基数排序
     */

//    public static void radixSort(int[] array,int[][] buckets) {
//        int num = getMax(array).toString().length();
//
//        while (num > 0) {
//            for (int i : array) {
//                int a = i % 10;
//            }
//
//            num --;
//        }
//
//    }
//
//    public static Integer getMax(int[] array) {
//        int max = array[0];
//        for (int i :array) {
//            if (i > max) {
//                max = i;
//            }
//        }
//        return max;
//    }


    /*
    chatGPT:
    首先找到数组中的最大值max，然后通过循环处理每一位数字（从个位到最高位），在每一位上使用计数排序算法进行排序。
    在计数排序算法中，我们首先创建一个计数数组count，用于记录每个数字出现的次数。
    然后，我们通过遍历数组，计算出每个数字的当前位上的数值，并将对应的计数器加一。
    接着，我们对计数数组进行前缀和处理，这样就可以得到每个数字在排序结果中的位置。
    最后，我们倒序遍历原数组，根据当前位上的数值和计数数组，将数字放入输出数组output的对应位置。
    最后，我们将输出数组的内容复制回原数组。

    时间复杂度为O(d(n+k))，其中d为数字位数，k为基数。
     */
    public static void radixSort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int max = getMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
    }

    private static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    private static void countingSort(int[] array, int exp) {
        int[] output = new int[array.length];
        int[] count = new int[10];

        for (int i = 0; i < array.length; i++) {
            int digit = (array[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = output[i];
        }
    }

}
