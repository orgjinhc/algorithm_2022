package leetcode.daily;

import static leetcode.SortUtil.reverse;

/**
 * 给你一个数组，将数组中的元素向右轮转 k'个位置，其中'k'是非负数。
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * <p>
 * 链接：https://leetcode-cn.com/problems/rotate-array
 */
public class Rotate_189 {

    public static void rotate(int[] nums, int k) {
        if (nums == null) {
            return;
        }
        int N = nums.length;
        if (nums.length < k) {
            k = k % N;
        }
        reverse(nums, 0, N - k - 1);
        reverse(nums, N - k, N - 1);
        reverse(nums, 0, N - 1);
    }
    public static void main(String[] args) {
        int[] nums = {1, 2};
        rotate(nums, 3);

        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
