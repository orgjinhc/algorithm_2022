package leetcode.daily.medium;

public class Daily_238_productExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] pre = pre(nums);
        int[] post = post(nums);
        nums[0] = post[0];
        nums[nums.length - 1] = pre[nums.length - 1];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = pre[i - 1] * post[i + 1];
        }
        return nums;
    }

    public static int[] pre(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i];
        }
        return ans;
    }

    public static int[] post(int[] nums) {
        int[] ans = new int[nums.length];
        ans[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            ans[i] = ans[i + 1] * nums[i];
        }
        return ans;
    }


}
