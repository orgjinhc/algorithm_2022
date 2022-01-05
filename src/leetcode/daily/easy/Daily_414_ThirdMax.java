package leetcode.daily.easy;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 * 示例 2：
 * <p>
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 * 示例 3：
 * <p>
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/third-maximum-number
 */
public class Daily_414_ThirdMax {

    public static int thirdMaxByPriorityQueue(int[] nums) {
        PriorityQueue<Integer> ans = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Set<Integer> set = new HashSet<>();
        int min = 0;
        for (int num : nums) {
            if (num == Integer.MIN_VALUE) {
                min = num;
                continue;
            }
            if (!set.contains(num)) {
                set.add(num);
                ans.add(num);
            }
        }
        int N = ans.size();
        if (N == 1) {
            return ans.poll();
        }
        if (N == 2) {
            return min == Integer.MIN_VALUE ? min : ans.poll();
        }
        for (int i = 0; i < 2; i++) {
            ans.poll();
        }
        return ans.isEmpty() ? min : ans.poll();
    }


    public static int thirdMax(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        boolean minFlag = false;
        int p1 = Integer.MIN_VALUE;
        int p2 = Integer.MIN_VALUE;
        int p3 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num == Integer.MIN_VALUE) {
                minFlag = true;
                continue;
            }
            if (p1 < num) {
                p1 = num;
            }
        }
        for (int num : nums) {
            if (p1 > num && num > p2) {
                p2 = num;
            }
        }
        for (int num : nums) {
            if (p2 > num && num > p3) {
                p3 = num;
            }
        }
        if (p3 != Integer.MIN_VALUE || (minFlag && p1 != Integer.MIN_VALUE && p2 != Integer.MIN_VALUE)) {
            return p3;
        } else {
            return p1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 1};
        System.out.println(thirdMax(nums));
    }
}