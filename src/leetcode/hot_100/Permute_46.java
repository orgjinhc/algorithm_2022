package leetcode.hot_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 链接：https://leetcode-cn.com/problems/permutations
 */
public class Permute_46 {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        DFS(nums, 0, ans);
        return ans;
    }

    private static void DFS(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> curAns = new ArrayList<>();
            for (int num : nums) {
                curAns.add(num);
            }
            ans.add(curAns);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            DFS(nums, index + 1, ans);
            swap(nums, index, i);
        }
    }

    private static void swap(int[] nums, int L, int R) {
        int tmp = nums[L];
        nums[L] = nums[R];
        nums[R] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> ans = permute(nums);
        for (List<Integer> list : ans) {
            for (Integer integer : list) {
                System.out.print(" " + integer);
            }
            System.out.println();
        }
    }

}
