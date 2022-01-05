package leetcode.daily.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，按要求返回一个新数组counts 。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于nums[i] 的元素的数量。
 * 示例 1：
 * 输入：nums = [5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 * 示例 2：
 * 输入：nums = [-1]
 * 输出：[0]
 * 示例 3：
 * 输入：nums = [-1,-1]
 * 输出：[0,0]
 * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 */
public class Daily_315_countSmaller {

    public static class Node {
        private int value;
        private int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            ans.add(0);
        }
        if (nums.length < 2) {
            return ans;
        }

        Node[] arr = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new Node(nums[i], i);
        }
        mergeSort(arr, 0, nums.length - 1, ans);
        return ans;
    }

    public static void mergeSort(Node[] nums, int L, int R, List<Integer> ans) {
        if (L == R) {
            return;
        }
        int M = (L + R) / 2;
        mergeSort(nums, L, M, ans);
        mergeSort(nums, M + 1, R, ans);
        merge(nums, L, M, R, ans);
    }

    /**
     * 核心流程
     * 逆序比较和排序
     * 相等情况先排序右边
     *
     * @param arr
     * @param left
     * @param mid
     * @param right
     * @param ans
     */
    public static void merge(Node[] arr, int left, int mid, int right, List<Integer> ans) {
        Node[] help = new Node[right - left + 1];
        int i = help.length - 1;
        int p1 = mid;
        int p2 = right;
        while (p1 >= left && p2 >= mid + 1) {
            if (arr[p1].value > arr[p2].value) {
                ans.set(arr[p1].index, ans.get(arr[p1].index) + p2 - mid);
            }
            help[i--] = arr[p1].value > arr[p2].value ? arr[p1--] : arr[p2--];
        }
        while (p1 >= left) {
            help[i--] = arr[p1--];
        }
        while (p2 >= mid + 1) {
            help[i--] = arr[p2--];
        }
        for (i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
    }

    public static void main(String[] args) {
//        int[] nums = {26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23};
        int[] nums = {5, 3, 2, 6, 1};
        System.out.println(countSmaller(nums));
    }
}
