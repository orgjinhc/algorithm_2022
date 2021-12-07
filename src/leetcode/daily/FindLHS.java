package leetcode.daily;

/**
 * https://leetcode-cn.com/problems/longest-harmonious-subsequence/
 */
public class FindLHS {

    public static int findLHS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }

        int maxCount = 0;
        int[] ans = new int[2];
        for (int L = 0; L < nums.length; L++) {
            int baseCount = 1;
            for (int i = L + 1; i < nums.length; i++) {
                if (nums[L] == nums[i]) {
                    baseCount++;
                }
                if (nums[L] - nums[i] == 1) {
                    ans[0] = ans[0] + 1;
                } else if (nums[L] - nums[i] == -1) {
                    ans[1] = ans[1] + 1;
                }
            }
            if (ans[1] != 0 || ans[0] != 0) {
                maxCount = Math.max(maxCount, Math.max(baseCount + ans[0], baseCount + ans[1]));
            }
            ans[0] = 0;
            ans[1] = 0;
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[] nums = new int[100000];
        for (int i = 0; i < 100000; i++) {
            nums[i] = i;
        }
        System.out.println(findLHS(nums));
    }
}
