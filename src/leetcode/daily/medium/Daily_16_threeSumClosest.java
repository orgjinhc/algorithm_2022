package leetcode.daily.medium;

import java.util.Arrays;

public class Daily_16_threeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int a = 0; a < nums.length; a++) {
            int b = a + 1;
            int c = nums.length - 1;
            while (b <= c - 1) {
                int sum = nums[b] + nums[c] + nums[a];
                if (sum == target) {
                    return sum;
                }
                //  获取最接近答案的和
                if (Math.abs(target - sum) < Math.abs(ans - target)) {
                    ans = sum;
                }

                if (sum > target) {
                    c--;
                } else {
                    b++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-3, -2, -5, 3, -4};
        System.out.println(threeSumClosest(nums, -1));
    }
}
