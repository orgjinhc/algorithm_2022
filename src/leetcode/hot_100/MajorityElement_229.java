package leetcode.hot_100;

import java.util.*;

/**
 * 升级:给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/majority-element-ii
 */
public class MajorityElement_229 {

    /**
     * 摩尔投票
     * 最优解
     *
     * @param nums
     * @return
     */
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int cand1 = 0;
        int count1 = 0;
        int cand2 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (cand1 == num) {
                count1++;
            } else if (cand2 == num) {
                count2++;
            } else if (count1 == 0) {
                cand1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                cand2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = count2 = 0;
        for (int num : nums) {
            if (cand1 == num) {
                count1++;
            } else if (cand2 == num) {
                count2++;
            }
        }
        if (count1 > nums.length / 3) {
            ans.add(cand1);
        }
        if (count2 > nums.length / 3) {
            ans.add(cand2);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        List<Integer> majorityElement = majorityElement(nums);
        for (Integer integer : majorityElement) {
            System.out.print(" " + integer);
        }
        System.out.println();
    }
}
