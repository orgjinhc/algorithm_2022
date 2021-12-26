package leetcode.top.easy;

import leetcode.util.LCUtil;

public class RemoveDuplicates_26 {

    /**
     * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
     * 输出：5, nums = [0,1,2,3,4]
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int S = 0;
        for (int F = 1; F < nums.length; F++) {
            if (nums[S] == nums[F]) {
                F++;
                continue;
            }
            LCUtil.swap(nums, S + 1, F);
            S++;
            F++;
        }
        return S + 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums));
    }
}