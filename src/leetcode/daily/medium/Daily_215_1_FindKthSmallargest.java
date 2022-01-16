package leetcode.daily.medium;


import leetcode.util.LCUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 提示：
 * 1 <= k <= nums.length <= 104
 * -104<= nums[i] <= 104
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 */
public class Daily_215_1_FindKthSmallargest {

    /**
     * 基于语言实现的排序库
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargestBySort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 自己基于快排实现
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        int N = nums.length;
        return quickSelect(nums, 0, N - 1, N - k);
    }

    public static int findKthSmallest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    public static int quickSelect(int[] nums, int left, int right, int index) {
        int pivot = left + new Random().nextInt(right - left + 1);
        LCUtil.swap(nums, pivot, right);
        int q = partition(nums, left, right);
        if (q == index) {
            return nums[q];
        } else {
            return q < index ? quickSelect(nums, q + 1, right, index) : quickSelect(nums, left, q - 1, index);
        }
    }

    /**
     * 核心逻辑:
     * 将 L...R 变为左右有序的两部分, 任意返回其中一部分即可
     * 定义一个小于L的 LessEquals 区域
     * 使用R作为分区数, 小于等于R的数用 LessEquals划分, L作为游标
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

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 3};
        System.out.println(findKthSmallest(nums, 9));
        System.out.println(findKthLargest(nums, 1));
    }
}