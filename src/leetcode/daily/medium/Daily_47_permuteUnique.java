package leetcode.daily.medium;

import leetcode.util.LCUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 */
public class Daily_47_permuteUnique {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> ans = new HashSet<>();
        DFS(nums, 0, ans, new ArrayList<>());
        return new ArrayList<>(ans);
    }

    public static void DFS(int[] nums, int index, Set<List<Integer>> ans, List<Integer> path) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            swap(nums, i, index);
            DFS(nums, index + 1, ans, path);
            path.remove(path.size() - 1);
            swap(nums, i, index);
        }
    }


    private static void swap(int[] nums, int L, int R) {
        int tmp = nums[L];
        nums[L] = nums[R];
        nums[R] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 3, 0, 3};
        LCUtil.print(permuteUnique(nums));
    }
}