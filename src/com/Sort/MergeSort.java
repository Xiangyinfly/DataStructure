package com.Sort;

public class MergeSort {
    /**
     * 归并排序
     */

    /*
    chatGPT：
    mergeSort方法是归并排序的入口方法，它将数组划分成左右两个子数组，然后递归地调用自己对左右子数组进行排序，最后调用merge方法将左右子数组合并成一个有序数组。
    merge方法中，我们创建了一个临时数组temp，然后通过指针i和j分别指向左右两个子数组的起始位置，比较两个指针所指向的元素大小，将较小的元素放入temp数组中，直到其中一个指针到达了子数组的末尾。
    最后，我们将剩余的元素放入temp数组中，再将temp数组中的元素复制回原数组的相应位置。
     */
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);//递归左侧数组
            mergeSort(array, mid + 1, right);//递归右侧数组
            merge(array, left, mid, right);//排序本数组
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;//temp数组的指针

        //依次比较数组左右两部分的头元素的大小，存入temp
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k] = array[i];
                i++;
            } else {
                temp[k] = array[j];
                j++;
            }
            k++;
        }
        //如果左部分未遍历完成，则直接加在temp后面
        while (i <= mid) {
            temp[k] = array[i];
            i++;
            k++;
        }
        //如果右部分未遍历完成，则直接加在temp后面
        while (j <= right) {
            temp[k] = array[j];
            j++;
            k++;
        }
        //将temp数组里的元素拷贝到原数组
        for (int m = 0; m < temp.length; m++) {
            array[left + m] = temp[m];
        }
    }

}
