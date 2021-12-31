package leetcode.hot_100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 时间O(N) 空间O(1)
 * <p>
 * 链接：https://leetcode-cn.com/problems/majority-element
 * <p>
 * <p>
 * 升级:给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 */
public class Hot_169_MajorityElement_169 {
    /**
     * 摩尔投票
     * 最优解
     *
     * @param nums
     * @return
     */
    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }


    /**
     * 摩尔投票
     * 最优解
     *
     * @param nums
     * @return
     */
    public static int majorityElement1(int[] nums) {
        int N = nums.length;
        int cand_num = nums[0];
        int count = 1;
        for (int i = 1; i < N; ++i) {
            if (cand_num == nums[i]) {
                ++count;
            } else if (--count == 0) {
                cand_num = nums[i];
                count = 1;
            }
        }
        return cand_num;
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> ans = new HashMap<>();
        for (int num : nums) {
            ans.put(num, ans.getOrDefault(num, 1) + 1);
        }

        int majority = 0;
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : ans.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                majority = entry.getKey();
            }
        }
        return majority;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        System.out.println(majorityElement(nums));
    }
}
