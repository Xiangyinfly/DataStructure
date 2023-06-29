package com.Recursion;

public class Queen8 {
    /**
     * array[i]表示第i+1个皇后的位置为array[i]
     */
    int[] array = new int[8];

    public static void main(String[] args) {

    }

    /**
     * @param n 表示第n个皇后
     * 该方法放置皇后
     */
    public void layQueen(int n) {
        if (n > 8) {
            print();
            return;
        }
        for (int i = 0; i < 8; i++) {
            array[n-1] = i + 1;
            if (judge(n)) {
                layQueen(n+1);
            }
        }
    }

    /**
     * @param n 表示第n个皇后
     * 该方法判断是否与摆放好的皇后冲突
     */
    public boolean judge(int n) {
        for (int i = 0; i < n - 1; i++) {
            //如果在同一列或对角线，返回false
            if (array[i] == array[n-1] ||
                    Math.abs(n-1-i) == Math.abs(array[n-1] - array[i])) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("第%d个皇后的位置为(%d,%d)\n",i + 1,i + 1,array[i]);
        }
    }
}
