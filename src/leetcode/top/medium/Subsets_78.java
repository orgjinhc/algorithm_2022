package leetcode.top.medium;

import leetcode.util.LCUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * <p>
 * 链接：https://leetcode-cn.com/problems/subsets
 */
public class Subsets_78 {

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 1) {
            ans.add(new ArrayList<>());
            ans.add(Arrays.asList(nums[0]));
            return ans;
        }
        process(nums, 0, ans, new ArrayList<>());
        return ans;
    }

    public static void process(int[] nums, int index, List<List<Integer>> ans, List<Integer> path) {
        if (index == nums.length) {
            ans.add(path);
            return;
        }

        List<Integer> selected = new ArrayList<>();
        selected.addAll(path);
        selected.add(nums[index]);
        process(nums, index + 1, ans, selected);

        List<Integer> unSelected = new ArrayList<>();
        unSelected.addAll(path);
        process(nums, index + 1, ans, unSelected);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> ans = subsets(nums);
        LCUtil.print(ans);
    }
}
