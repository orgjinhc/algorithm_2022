package leetcode.hot_100;


import java.util.*;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 */
public class LongestConsecutive_128 {

    /**
     * 输入：nums = [100,4,200,1,3,2]
     * 输出：4
     * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
     * 示例 2：
     * <p>
     * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
     * 输出：9
     *
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        int ans = 0;
        if (nums.length < 1) {
            return ans;
        }
        Arrays.sort(nums);
        //  重复指针
        int D = 0;
        //  双指针
        int L = 0;
        int R = 0;
        //  右指针不越界就不断的去尝试计算最大距离
        while (R < nums.length) {
            if (R > 0 && nums[R] == nums[R - 1]) {
                D++;
                R++;
                continue;
            }
            //  非连续情况出现
            if (R > 0 && nums[R] != (++nums[R - 1])) {
                ans = Math.max(R - L - D, ans);
                L = R;
                D = 0;
            }
            R++;
        }
        //  边界情况, 所有位置都没有重复
        return Math.max(ans, R - L - D);
    }


    public static void main(String[] args) {
        int[] nums = {4, 0, -4, -2, 2, 5, 2, 0, -8, -8, -8, -8, -1, 7, 4, 5, 5, -4, 6, 6, -3};
        System.out.println(longestConsecutive(nums));
    }
}
