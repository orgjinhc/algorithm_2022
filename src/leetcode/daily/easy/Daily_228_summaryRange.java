package leetcode.daily.easy;

import java.util.ArrayList;
import java.util.List;

public class Daily_228_summaryRange {

    public List<String> summaryRanges(int[] nums) {

        List<String> list = new ArrayList<>();
        for (int low = 0, high = 0; high < nums.length; high++) {
            if (high + 1 < nums.length && nums[high + 1] == nums[high] + 1) {
                continue;
            }
            if (low == high) {
                list.add(nums[low] + "");
            } else {
                list.add(nums[low] + "->" + nums[high]);
            }
            low = high + 1;
        }
        return list;
    }
}
