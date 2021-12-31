package leetcode.top_200.easy;


import java.util.Arrays;

/**
 * 给定一个包含 [0, n]中n个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 3：
 * <p>
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 4：
 * <p>
 * 输入：nums = [0]
 * 输出：1
 * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * nums 中的所有数字都 独一无二
 * <p>
 * 进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
 * <p>
 * 链接：https://leetcode-cn.com/problems/missing-number
 */
public class Top_268_MissingNumber {

    public static int missingNumberByDP(int[] nums) {
        int N = nums.length;

        //  预处理数组
        boolean[] dp = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            dp[nums[i]] = true;
        }

        //  找到没有设置true的位置
        for (int i = 0; i < dp.length; i++) {
            if (!dp[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int missingNumberBySort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return -1;
    }

    public static int missingNumberByBit(int[] nums) {
        int ans = 0;
        for (int i = 0; i <= nums.length; i++) {
            ans ^= i;
        }
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(missingNumberByBit(nums));
    }
}