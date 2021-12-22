package leetcode.hot_100;

/**
 * 给定一个非负整数数组nums ，你最初位于数组的 第一个下标 。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标。
 * <p>
 * 示例1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * 0 <= nums[i] <= 105
 * <p>
 * 链接：https://leetcode-cn.com/problems/jump-game
 */
public class CanJump_55 {

    public static boolean canJump(int[] nums) {
        int N = nums.length;
        if (N == 1) {
            return true;
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != 0) {
                continue;
            }
            boolean canJump = false;
            //  开始统计0...i-1是否能跳过当前屏障
            int L = i;
            //  不越界
            while (L-- >= 0) {
                //  当前障碍能跳过, 当前障碍是否是最后一个障碍
                if ((nums[L] - (i - L) > 0) || (i == N - 1 && nums[L] - (i - L) >= 0)) {
                    canJump = true;
                    break;
                }
            }
            if (!canJump) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 0};
        System.out.println(canJump(nums));
    }
}
