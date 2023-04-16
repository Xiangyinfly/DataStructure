package com.Sort;

public class HeapSort {
    /*
     * 堆排序
     */

    public static void heapSort(int[] array) {
        int temp = 0;
        //将数组转化为大顶堆
        for (int i = array.length / 2 - 1; i >= 0; i --) {
            adjustHeap(array,i,array.length);
        }

        for (int j = array.length - 1; j > 0; j --) {
            //交换堆顶元素和最后一个元素
            temp = array[j];
            array[j] = array[0];
            array[0] = temp;

            adjustHeap(array,0,j);
        }
    }

    /**
     * 将某个非叶子结点的树转为大顶堆
     * @param array 待调整的数组
     * @param index 非叶子结点在数组中的索引
     * @param length 剩余待调整的元素，逐渐减少
     */
    public static void adjustHeap(int[] array, int index, int length) {
        int temp = array[index];

        for (int i = index * 2 + 1;i < length;i = i * 2 + 1) {
            //如果非叶子结点的右子节点大于左子节点，i指向右子节点
            if (i + 1 < length && array[i] < array[i + 1]) {
                i ++;
            }
            //如果子节点大于父节点，交换两者的值
            if (array[i] > temp) {
                array[index] = array[i];
                index = i;
            } else {
                break;//从下往上排序，所以可以直接break
            }
        }
        array[index] = temp;
    }
}
