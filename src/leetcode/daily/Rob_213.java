package leetcode.daily;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
 * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * 链接：https://leetcode-cn.com/problems/house-robber-ii
 */
public class Rob_213 {

    public int rob(int[] nums) {
        if (nums == null || nums.length <1) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int N = nums.length;
        //  dp1[i]:nums[0...i]范围上得到的最大累加和。但是，任何相邻两个数不能选择
        int[] dp1 = new int[N - 1];
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < N - 1; i++) {
            //  自己是最大金额情况
            int p1 = nums[i];
            //  前一个位置是最大金额情况
            int p2 = dp1[i - 1];
            //  当前位置 + 不相邻的前一个位置是最大金额的情况
            int p3 = dp1[i - 2] + nums[i];

            //  三种情况求最大是当前i的累加和
            dp1[i] = Math.max(p1, Math.max(p2, p3));
        }

        int[] dp2 = new int[N - 1];
        dp2[1] = Math.max(nums[1], nums[2]);

        for (int i = 2; i < N; i++) {
            //  自己是最大金额情况
            int p1 = nums[i];
            //  前一个位置是最大金额情况
            int p2 = dp2[i - 1];
            //  当前位置 + 不相邻的前一个位置是最大金额的情况
            int p3 = dp2[i - 2] + nums[i];

            //  三种情况求最大是当前i的累加和
            dp2[i] = Math.max(p1, Math.max(p2, p3));
        }
        return Math.max(dp1[N - 2], dp2[N - 1]);
    }
}
