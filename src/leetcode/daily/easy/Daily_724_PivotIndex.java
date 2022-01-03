package leetcode.daily.easy;

import java.util.Arrays;

public class Daily_724_PivotIndex {

    public static int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
//
//        int[] preSum = preSum(nums);
//        int[] postSum = postSum(nums);
//        for (int i = 0; i < nums.length; i++) {
//            if (preSum[i] == postSum[i]) {
//                return i;
//            }
//        }
//        return -1;
    }

    public static int[] preSum(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        ans[0] = nums[0];
        for (int i = 1; i < N; i++) {
            ans[i] = nums[i] + ans[i - 1];
        }
        return ans;
    }

    public static int[] postSum(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        ans[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            ans[i] = nums[i] + ans[i + 1];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, -1};
        System.out.println(pivotIndex(nums));
    }
}
