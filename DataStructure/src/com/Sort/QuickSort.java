package com.Sort;

public class QuickSort {
    /**
     * 快速排序
     * 在最糟情况下，栈长为O(n)；在最佳情况下，栈长为O(log n)
     *
     * 每层需要的时间为O(n)，因此最佳情况时间复杂度为O(n) * O(log n) = O(n log n)，最糟情况时间复杂度为O(n) * O(n) = O(n^2)
     */


    /**
     * 以中间值为基准
     * @param array 需要排序的数组
     * @param left 二分的左端
     * @param right 二分的右端
     */
    public static void quickSort1(int[] array,int left,int right) {
        //定义l/r为左右指针
        int l = left;
        int r = right;
        //中轴值
        int pivot = array[(left + right) / 2];
        int temp = 0;

        while (l < r) {
            while (array[l] < pivot) {
                l ++;
            }
            while (array[r] > pivot) {
                r --;
            }
            if (l >= r) {
                break;
            }

            //交换
            temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            //如果出现与array[pivot]相等的值，跳过，否则出现无限循环
            if (array[l] == pivot) {
                r --;
            }
            if (array[r] == pivot) {
                l ++;
            }
        }

        //如果恰好l和r相遇在一个与array[pivot]相等的值的位置，则空过该值对两侧组进行quickSort1
        if (l == r) {
            l ++;
            r --;
        }
        if (left < r) {
            quickSort1(array,left,r);
        }
        if (right > l) {
            quickSort1(array,l,right);
        }
    }


    /*
    以最左侧值为基准 来自chatGPT
    动画演示 https://www.bilibili.com/video/BV1at411T75o
    方法：
    首先，我们选取数组中的一个元素作为基准值，然后将数组划分为两个部分，其中一个部分包含所有小于基准值的元素，另一个部分包含所有大于基准值的元素。
    接着，我们递归地对这两个部分进行快速排序，直到整个数组都被排序完毕。

    partition 方法用于实现数组的划分，它从左右两端开始查找，找到一个左边的元素小于基准值，右边的元素大于基准值时，就交换它们的位置。
    当左右两端相遇时，就将基准值移动到最终的位置，并返回该位置，以便进行下一轮划分。
     */
    public static void quickSort2(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right); // 将数组划分为两个部分
            quickSort2(arr, left, pivotIndex - 1); // 递归对左半部分进行快速排序
            quickSort2(arr, pivotIndex + 1, right); // 递归对右半部分进行快速排序
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[left]; // 选取左边的元素作为基准值
        int l = left, r = right;

        while (l < r) {
            while (l < r && arr[r] >= pivot) {
                r--; // 从右边开始找到第一个小于基准值的元素
            }
            arr[l] = arr[r]; // 将该元素移到左边

            while (l < r && arr[l] <= pivot) {
                l++; // 从左边开始找到第一个大于基准值的元素
            }
            arr[r] = arr[l]; // 将该元素移到右边
        }

        arr[l] = pivot; // 将基准值放入最终的位置
        return l;
    }
}
