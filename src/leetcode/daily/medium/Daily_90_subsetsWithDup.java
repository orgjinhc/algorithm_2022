package leetcode.daily.medium;

import java.util.*;

public class Daily_90_subsetsWithDup {

    List<Integer> t = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(false, 0, nums);
        return ans;
    }

    public void dfs(boolean choosePre, int index, int[] nums) {
        if (index == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        dfs(false, index + 1, nums);
        if (!choosePre && index > 0 && nums[index - 1] == nums[index]) {
            return;
        }
        t.add(nums[index]);
        dfs(true, index + 1, nums);
        t.remove(t.size() - 1);
    }
}