package leetcode.daily.medium;

import java.util.ArrayList;
import java.util.Arrays;
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
public class Daily_39_CombinationSum {

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
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        DFS(candidates, target, ans, combine, 0);
        return ans;
    }

    public static void DFS(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int index) {
        if (index == candidates.length) {
            return;
        }
        //  找到一组答案
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }
        //  直接跳过
        DFS(candidates, target, ans, combine, index + 1);
        //  计算剩余数
        int remainingNum = target - candidates[index];
        //  满足条件继续递归当前数
        if (remainingNum >= 0) {
            combine.add(candidates[index]);
            DFS(candidates, remainingNum, ans, combine, index);
            combine.remove(combine.size() - 1);
        }
    }

    public static List<List<Integer>> customCombinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public static void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int index) {
        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;

        }
        for (int i = index; i < candidates.length; i++) {
            int remainingNum = target - candidates[i];
            if (remainingNum < 0) {
                break;
            }
            //  添加答案
            combine.add(candidates[i]);
            //  以当前位置不断的进行尝试, 直到不满足的位置出现后。最后一次不满足场景退出
            dfs(candidates, remainingNum, ans, combine, i+1);
            //  恢复现场
            combine.remove(combine.size() - 1);
        }
    }

    /**
     * 前缀和
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

    /**
     * 后缀和
     *
     * @param nums
     * @return
     */
    public static int[] suffixSum(int[] nums) {
        int N = nums.length;
        int[] ans = new int[N];
        int index = N - 1;
        ans[index] = nums[index];
        for (int i = N - 2; i >= 0; i--) {
            ans[index - 1] = ans[index--] + nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,16,14,15};
        List<List<Integer>> ans = customCombinationSum(nums, 20);
        for (List<Integer> list : ans) {
            for (Integer integer : list) {
                System.out.print(" " + integer);
            }
            System.out.println();
        }
    }
}
