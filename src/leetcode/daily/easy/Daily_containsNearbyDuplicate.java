package leetcode.daily.easy;

import java.util.HashMap;
import java.util.Map;

public class Daily_containsNearbyDuplicate {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        Map<Integer, Integer> ans = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (ans.containsKey(nums[i])) {
                return k >= Math.abs(nums[i] - nums[ans.get(nums[i])]);
            }
            ans.put(nums[i], i);
        }
        return false;
    }
}
