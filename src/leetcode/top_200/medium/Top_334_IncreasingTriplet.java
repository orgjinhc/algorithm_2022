package leetcode.top_200.medium;

/**
 * 给你一个整数数组nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * <p>
 * 如果存在这样的三元组下标 (i, j, k)且满足 i < j < k ，使得nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4,5]
 * 输出：true
 * 解释：任何 i < j < k 的三元组都满足题意
 * 示例 2：
 * <p>
 * 输入：nums = [5,4,3,2,1]
 * 输出：false
 * 解释：不存在满足题意的三元组
 * 示例 3：
 * <p>
 * 输入：nums = [2,1,5,0,4,6]
 * 输出：true
 * 解释：三元组 (3, 4, 5) 满足题意，因为 nums[3] == 0 < nums[4] == 4 < nums[5] == 6
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * 链接：https://leetcode-cn.com/problems/increasing-triplet-subsequence
 */
public class Top_334_IncreasingTriplet {

    /**
     * 贪心思路
     * 1.先找一个第一小
     * 2.再找一个比第一小大的第二小的值, 永远只更新第一小
     * 3.找到一个比第二小大的就返回
     *
     * @param nums
     * @return
     */
    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        //  9, 10, 5, 11, 10, 9, 8
        int FMin = Integer.MAX_VALUE;
        int SMin = Integer.MAX_VALUE;
        for (int curNum : nums) {
            if (curNum > SMin) {
                return true;
            }
            if (FMin >= curNum) {
                FMin = curNum;
            } else {
                SMin = curNum;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {9, 10, 5, 11, 10, 9, 8};
        System.out.println(increasingTriplet(nums));
    }

}
