package leetcode.daily.medium;

import java.util.HashMap;

public class Daily_454_fourSumCount {

    /**
     * 双表实现
     *
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] = nums2[j];
                dp.put(sum, dp.getOrDefault(sum, 0) + 1);
            }
        }

        int ans = 0;
        for (int i = 0; i < nums3.length; i++) {
            for (int j = 0; j < nums4.length; j++) {
                int sum = nums3[i] = nums4[j];
                if (dp.containsKey(-sum)) {
                    ans += dp.get(-sum);
                }
            }
        }
        return ans;
    }
}
