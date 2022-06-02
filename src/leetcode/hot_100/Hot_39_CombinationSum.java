package leetcode.hot_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target，找出candidates中所有可以使数字和为目标数target的唯一组合。
 * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。
 * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
public class Hot_39_CombinationSum {

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
        }
        for (int i = index; i < candidates.length; i++) {
            int remainingNum = target - candidates[i];
            if (remainingNum < 0) {
                break;
            }
            //  添加答案
            combine.add(candidates[i]);
            //  从当前位置不断进行尝试, 直到不满足的位置出现后。最后一次不满足场景退出
            dfs(candidates, remainingNum, ans, combine, i);
            //  恢复现场
            combine.remove(combine.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 7};
        List<List<Integer>> ans = customCombinationSum(nums, 7);
        for (List<Integer> list : ans) {
            for (Integer integer : list) {
                System.out.print(" " + integer);
            }
            System.out.println();
        }
    }
}
