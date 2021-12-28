package leetcode.hot_100;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 链接：https://leetcode-cn.com/problems/house-robber
 */
public class Hot_198_Rob_198 {

    public int rob(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int N = nums.length;
        if (N == 1) {
            return nums[0];
        }
        //  dp[i]含义:nums数组从0位置...i位置上不相邻的数的累加和
        int[] dp = new int[N];
        //  前两个位置先构建出来
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        if (N == 2) {
            return dp[1];
        }
        //  开始构建dp[i]
        for (int i = 2; i < N; i++) {
            //  第一种情况, 当前位置就是最大金额
            int p1 = nums[i];
            //  第二种情况, 前一位置是最大金额
            int p2 = nums[i - 1];
            //  第三种情况, 当前位置 + 前一不相邻的位置是最大金额
            int p3 = nums[i - 2] + p1;
            //  当前位置的最终答案是, 三种情况的最大情况
            dp[i] = Math.max(p1, Math.max(p2, p3));
        }
        return dp[N - 1];
    }

    public static void main(String[] args) {

    }
}
