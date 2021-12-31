package leetcode.hot_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Hot_53_MaxSubArray_53 {

    /**
     * @param nums
     * @return
     */
    public static int maxSubArrayDp(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     * ac失败
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        List<List<Integer>> subSequence = subSequence(nums);
        int ans = Integer.MIN_VALUE;
        for (List<Integer> list : subSequence) {
            int sum = 0;
            for (Integer integer : list) {
                sum += integer;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    /**
     * 子序列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subSequence(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                List<Integer> subList = new ArrayList<>();
                for (int k = i; k <= j; k++) {
                    subList.add(nums[k]);
                }
                ans.add(subList);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }
}
