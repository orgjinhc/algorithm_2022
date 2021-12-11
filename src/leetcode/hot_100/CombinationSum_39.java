package leetcode.hot_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target，找出candidates中所有可以使数字和为目标数target的唯一组合。
 * <p>
 * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 * <p>
 * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
 * <p>
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
public class CombinationSum_39 {

    /**
     * 输入: candidates = [2,3,6,7], target = 7
     * 输出: [[7],[2,2,3]]
     * <p>
     * 输入: candidates = [2,3,5], target = 8
     * 输出: [[2,2,2,2],[2,3,3],[3,5]]
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ansList = new ArrayList<>();
        if (candidates == null || candidates.length < 1) {
            return ansList;
        }

        for (int L = 0; L < candidates.length; L++) {
            int candidate = candidates[L];
            if (candidate == target) {
                List<Integer> ans = new ArrayList<>();
                ans.add(target);
                ansList.add(ans);
            } else if (target % candidate == 0) {
                List<Integer> ans = new ArrayList<>();
                int size = target / candidate;
                while (size > 0) {
                    ans.add(candidate);
                    size--;
                }
                ansList.add(ans);
            } else {

            }

        }

        return null;
    }

    /**
     * 
     * @param nums
     * @return
     */
    public static int[] prefixSum(int[] nums) {
        int[] ans = new int[nums.length];
        int index = 0;
        ans[index] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans[index + 1] = ans[index++] + nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,5};
        int[] ints = prefixSum(nums);
        for (int i : ints) {
            System.out.print(" " + i);
        }
        System.out.println();
    }
}
