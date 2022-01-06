package leetcode.daily.medium;

import java.util.*;

public class Daily_18_fourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        if (nums.length < 4) {
            return ans;
        }
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for (int a = 0; a < nums.length - 3; a++) {
            for (int b = a + 1; b < nums.length - 2; b++) {
                int tmp = target - nums[a] - nums[b];
                int d = nums.length - 1;
                for (int c = b + 1; c < nums.length - 1; c++) {
                    while (c < d && nums[c] + nums[d] > tmp) {
                        d--;
                    }
                    if (c >= d) {
                        continue;
                    }
                    if (nums[c] + nums[d] == tmp) {
                        List<Integer> curAns = Arrays.asList(nums[a], nums[b], nums[c], nums[d]);
                        if (set.add(curAns)) {
                            ans.add(curAns);
                        }
                    }
                }
            }
        }
        return ans;
    }

}