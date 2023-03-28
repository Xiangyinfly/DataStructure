package com.Sort;

public class RadixSort {
    /**
     * 基数排序
     */

    /*
    我的方法：
    buckets第一个[]用来区分某位的数字(0-9)，第二个[]用来存放数据。
    其中buckets[x][0]用来记录buckets[x]存放数据的个数，因此有效数据从buckets[x][1]开始存放

    疑问：空间复杂度如何？如何优化？
     */
    public static void radixSort(int[] array) {
        int[][] buckets;//二维数组按每位大小储存数据
        int num = (getMax(array) + "").length();//得到最大元素的位数
        int loop = 0;

        while (loop < num) {
            //初始化buckets，二维数组中每个一位数组的第一个位置放置该一位数组有多少个有效元素（便于只遍历有效元素），因此[array.length + 1]
            buckets = new int[10][array.length + 1];
            for (int i : array) {
                int a = (int) (i / (Math.pow(10,loop))) % 10;//取出数据每一位，从个位开始
                buckets[a][++ buckets[a][0]] = i;//放置在buckets中
            }

            int k = 0;
            //将buckets中的数据拷贝到array中，进行下一位的排序
            for (int i = 0; i < 10; i++) {
                for (int j = 1; j <= buckets[i][0]; j++) {
                    array[k ++] = buckets[i][j];
                }
            }

            loop ++;
        }
    }

    public static int getMax(int[] array) {
        int max = array[0];
        for (int i :array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }


    /*
    网课方法：
     */
    public static void radixSort1(int[] array){
        int[][] bucket = new int[10][array.length];
        int[] bucketElementCounts = new int[10];
        int index;

        int max = array[0];
        for (int i = 1; i < array.length; i++){
            if (max < array[i]){
                max = array[i];
            }
        }
        int maxLength = (max+"").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *=10) {
            for (int j = 0; j < array.length; j++){
                int digitOfElement = array[j] / n % 10;
                // 放入到对应的桶
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++;
            }
            // 将桶里的数据放入
            index = 0;
            // 遍历每一个桶,将桶里的数据放入原来的数组中
            for (int k = 0;k < bucket.length; k++){
                if (bucketElementCounts[k] != 0){
                    for (int j = 0; j < bucketElementCounts[k]; j++){
                        array[index++] = bucket[k][j];
                    }
                }
                bucketElementCounts[k]= 0;
            }
        }
    }


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
    public static void radixSort2(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int max = getMax2(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(array, exp);
        }
    }

    private static int getMax2(int[] array) {
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
