package leetcode.daily.medium;

import leetcode.util.LCUtil;

import java.util.*;

/**
 * 给你一个由候选元素组成的集合 candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates 中的每个元素在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 * 示例1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 */
public class Daily_40_combinationSum2 {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        ArrayDeque combination = new ArrayDeque();
        Arrays.sort(candidates);
        DFS(candidates, target, 0, combination, ans);
        return ans;
    }

    public static void DFS(int[] candidates, int remainingNum, int index, ArrayDeque<Integer> combination, List<List<Integer>> ans) {
        if (remainingNum == 0) {
            ans.add(new ArrayList<>(combination));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            //  大剪枝
            //  如果剩余需要找的数字已经比当前位置的数小, 无需继续寻找, 直接退出当前位置
            int res = remainingNum - candidates[i];
            if (res < 0) {
                break;
            }
            //  小剪枝, 同一层的相同数值的不同位置, 无需再次尝试
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            //  当前数可能是候选答案, 添加到集合
            combination.addLast(candidates[i]);
            //  剩余数减去当前数, 继续尝试下一个位置(i or i+1, 取决于一个数是否可以被重复利用)
            DFS(candidates, res, i + 1, combination, ans);
            //  恢复现场
            combination.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] candidates = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        LCUtil.print(combinationSum2(candidates, 30));
    }
}