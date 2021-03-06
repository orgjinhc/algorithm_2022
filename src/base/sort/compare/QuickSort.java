package base.sort.compare;

import leetcode.util.LCUtil;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = {2, 1, 3, 4, 7, 6, 9, 1, 8, 7};
        quickSort(nums, 0, nums.length - 1);
        LCUtil.print(nums);
        System.out.println();
        splitNum2(nums);
        LCUtil.print(nums);
    }

    /**
     * 快排实现
     * base case:如果L>=R无需继续排序
     * 1.找到mid位置
     * 2.继续L到mid-1
     * 3.继续mid+1到R
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void quickSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //  保证 mid 左右有序
        int partition = partition(arr, L, R);
        //  保证左边有序
        quickSort(arr, L, partition - 1);
        //  保证右边有序
        quickSort(arr, partition + 1, R);
    }

    /**
     * 边界点
     *
     * @param arr
     * @param L   边界
     * @param R   边界
     * @return
     */
    public static int partition(int[] arr, int L, int R) {
        int lessEquals = L - 1;
        while (L < R) {
            if (arr[L] <= arr[R]) {
                LCUtil.swap(arr, ++lessEquals, L);
            }
            L++;
        }
        LCUtil.swap(arr, ++lessEquals, R);
        return lessEquals;
    }


    /**
     * 荷兰国旗问题
     * 2步
     * - 小于情况小于区域和源数组进行交互，两个指针分别++
     * - 大于情况源数组指针++即可
     *
     * @param arr
     */
    public static void splitNum2(int[] arr) {
        int index = 0;
        int N = arr.length - 1;
        int lessEqualsR = -1;
        int moreEqualsR = N - 1;
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
     * 划分小于等于区域
     * <p>
     * - 小于情况小于区域和源数组进行交互，两个指针分别++
     * - 大于情况源数组指针++即可
     *
     * @param arr
     */
    public static void splitNum(int[] arr) {
        int index = 0;
        int lessEqualsR = -1;
        int N = arr.length - 1;
        while (index < N) {
            if (arr[index] <= arr[N - 1]) {
                LCUtil.swap(arr, ++lessEqualsR, index);
            }
            index++;
        }
    }
}