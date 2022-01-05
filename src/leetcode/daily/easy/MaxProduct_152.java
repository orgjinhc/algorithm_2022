package leetcode.daily.easy;

/**
 * 给你一个整数数组 nums'，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释:'子数组 [2,3] 有最大乘积 6。
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 */
public class MaxProduct_152 {

    public static int maxProduct(int[] nums) {
        int N = nums.length;
        int[] dpMax = new int[N];
        int[] dpMin = new int[N];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            //  三种可能性分析
            //  1.当前位置自己
            int case1 = nums[i];
            //  2.当前 i 位置 * [i - 1] dpMax(正数)
            int case2 = case1 * dpMax[i - 1];
            //  3.当前 i 位置 * [i - 1] dpMin(负数)
            int case3 = case1 * dpMin[i - 1];

            //  更新dp
            dpMax[i] = Math.max(case1, Math.max(case2, case3));
            dpMin[i] = Math.min(case1, Math.min(case2, case3));

            //  更新答案
            ans = Math.max(ans, dpMax[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, -2, 4};
        System.out.println(maxProduct(nums));

    }
}
