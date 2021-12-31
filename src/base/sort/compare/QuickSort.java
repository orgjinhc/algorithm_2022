package base.sort.compare;

import leetcode.util.LCUtil;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {2, 1, 3, 4, 7, 6, 9, 7, 8, 7};
        quickSort(nums, 0, nums.length - 1);

        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    public static void quickSort(int[] arr, int L, int R) {
        if (null == arr || arr.length < 2) {
            return;
        }
        process(arr, L, R);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //  保证 mid 左右有序
        int mid = partition(arr, L, R);
        //  保证左边有序
        process(arr, L, mid - 1);
        //  保证右边有序
        process(arr, mid + 1, R);
    }


    /**
     * 边界点
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        //  将 L...R 变为左右有序的两部分, 任意返回其中一部分即可
        int lessEquals = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                LCUtil.swap(arr, index, ++lessEquals);
            }
            index++;
        }
        LCUtil.swap(arr, ++lessEquals, R);
        return lessEquals;
    }


    /**
     * 荷兰国旗问题
     * 2步
     * - 划分小于等于区域
     * - 小于情况小于区域和源数组进行交互，两个指针分别++
     * - 大于情况源数组指针++即可
     *
     * @param arr
     */
    public static void splitNum2(int[] arr) {
        int N = arr.length - 1;
        int lessEqualsR = -1;
        int moreEqualsR = arr.length - 1;
        int index = 0;
        while (index < moreEqualsR) {
            if (arr[index] < arr[N]) {
                LCUtil.swap(arr, ++lessEqualsR, index++);
            } else if (arr[index] > arr[N]) {
                LCUtil.swap(arr, --moreEqualsR, index);
            } else {
                index++;
            }
        }
        LCUtil.swap(arr, N, moreEqualsR);
    }


    /**
     * 荷兰国旗问题
     * 2步
     * - 划分小于等于区域
     * - 小于情况小于区域和源数组进行交互，两个指针分别++
     * - 大于情况源数组指针++即可
     *
     * @param arr
     */
    public static void splitNum(int[] arr) {
        int lessEqualsR = -1;
        int index = 0;
        int N = arr.length - 1;
        while (index < N) {
            if (arr[index] <= arr[N - 1]) {
                LCUtil.swap(arr, ++lessEqualsR, index);
            }
            index++;
        }
    }
}