package leetcode.daily.game;

import leetcode.util.LCUtil;

import java.util.ArrayList;
import java.util.List;

public class Game_278_2_maxScoreIndices {

    public static List<Integer> maxScoreIndices(int[] nums) {
        int N = nums.length;
        int[] dp = new int[2];
        for (int num : nums) {
            if (num == 1) {
                dp[1]++;
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int maxScore = dp[1];
        for (int i = 0; i <= N; i++) {
            int curScore = f(nums, i, dp);
            if (curScore > maxScore) {
                maxScore = curScore;
                ans.clear();
                ans.add(i);
            } else if (curScore == maxScore) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static int f(int[] nums, int index, int[] dp) {
        if (index > 0 && index <= nums.length) {
            int preIndex = nums[index - 1];
            if (preIndex == 0) {
                dp[0]++;
            } else {
                dp[1]--;
            }
        }
        return dp[0] + dp[1];
    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 0};
        LCUtil.printListInteger(maxScoreIndices(nums));
    }

}
