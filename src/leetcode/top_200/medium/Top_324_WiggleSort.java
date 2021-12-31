package leetcode.top_200.medium;


import leetcode.util.LCUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组nums，将它重新排列成nums[0] < nums[1] > nums[2] < nums[3]...的顺序。
 * <p>
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 * 示例 2：
 * <p>
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 104
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 * <p>
 * 进阶：你能用O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/wiggle-sort-ii
 */
public class Top_324_WiggleSort {

    /**
     * 核心思路
     * 源数组:1,2,3,4,5,6
     * 1,2,3
     * 4,5,6
     * 3,6,2,5,1,4
     * 数组排序后, 中位拆分为两部分
     * 两部分倒序回填到原数组即可
     * <p>
     * nums[0] < nums[1] > nums[2] < nums[3]
     * nums[2] < nums[3] > nums[4] < nums[5]
     * nums[4] < nums[5] > nums[6] < nums[7]
     * <p>
     * 输入：nums = [1,5,1,1,6,4]
     * 输出：[1,6,1,5,1,4]
     * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
     * <p>
     * 输入：nums = [1,3,2,2,3,1]
     * 输出：[2,3,1,3,1,2]
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        //  数组排序后, 中位拆分为两部分
        //  两部分倒序回填到原数组即可
        int[] help = nums.clone();
        Arrays.sort(help);
        int N = nums.length;
        for (int i = 1; i < nums.length; i += 2) {
            nums[i] = help[--N];
        }
        for (int i = 0; i < nums.length; i += 2) {
            nums[i] = help[--N];
        }
    }

    private static void DFS(int[] nums, int index) {
        if (index + 2 == nums.length) {
            return;
        }

        int p1 = nums[index];
        int p2 = nums[index + 1];
        int p3 = nums[index + 2];
        int p4 = nums[index + 3];
        //  nums[0] < nums[1] > nums[2] < nums[3]
        if (p1 > p2) {
            LCUtil.swap(nums, index, index + 1);
        }

        if (p3 > p2) {
            LCUtil.swap(nums, index + 2, index + 1);
        }

        if (p4 < p3) {
            LCUtil.swap(nums, index + 2, index + 3);
        }

        if (p1 < p2 && p2 > p3 && p3 < p4) {
            DFS(nums, index + 2);
        }
        //  开始尝试交互逻辑

    }

    public static void main(String[] args) {

    }
}
