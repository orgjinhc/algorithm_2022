package leetcode.hot_100;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 链接：https://leetcode-cn.com/problems/3sum
 * <p>
 * 特判，对于数组长度 nn，如果数组为 null null 或者数组长度小于 33，返回 [][]。
 * 对数组进行排序。
 * 遍历排序后数组：
 * 若 nums[i]>0 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 00，直接返回结果。
 * 对于重复元素：跳过，避免出现重复解
 * 令左指针 L=i+1 L=i+1，右指针 R=n-1 R=n−1，当 L<R L<R 时，执行循环：
 * 当 nums[i]+nums[L]+nums[R]==0nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,RL,R 移到下一位置，寻找新的解
 * 若和大于 00，说明 nums[R]nums[R] 太大，RR 左移
 * 若和小于 00，说明 nums[L]nums[L] 太小，LL 右移
 */
public class Hot_15_ThreeSum {


    /**
     * 优化1, 解决超时问题
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return ans;
        }
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        //  暴力遍历数组, 从头开始不断尝试答案, 外层循环控制a的起始位置, c的位置每次都是末尾元素
        for (int a = 0; a < nums.length; a++) {
            //  排序后的数组没有负数, 不可能存在三数相加等于0的情况
            if (nums[a] > 0) {
                return ans;
            }
            int c = nums.length - 1;
            //  内层循环控制b的起始位置, 每次都是从a的位置+1开始, 到c-1为止, b的位置单次循环不会变更, 每次变动c的位置来平衡结果
            for (int b = a + 1; b < c; b++) {
                //  ab之和大于等于0就不用继续尝试了, 直接跳出当前b位置的循环体, 已经没有答案了
                //  abc三数之和小与0, 也不用继续了
                if ((nums[a] + nums[b] > 0) || ((nums[a] + nums[b] + nums[c]) < 0)) {
                    continue;
                }

                //  如果abc之和大与0, 需要寻找c的位置
                while ((nums[a] + nums[b] + nums[c]) > 0 && (b < c--)) {

                }

                //  b和c的位置重合了也不用尝试了
                //  b位置大于c也不用尝试, 已经找到过这个答案
                if (b >= c) {
                    continue;
                }

                if (nums[a] + nums[b] + nums[c] == 0) {
                    List<Integer> curLevelAns = new ArrayList<>();
                    curLevelAns.add(nums[a]);
                    curLevelAns.add(nums[b]);
                    curLevelAns.add(nums[c]);
                    if (set.add(curLevelAns)) {
                        ans.add(curLevelAns);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 核心思路
     * 1.全排列
     * 2.所有长度为3的数组收集起来
     * 3.收集后的数组进行求和, 过滤出满足条件的数组
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        if (null == nums || nums.length < 1) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();

        HashSet<List<Integer>> set = new HashSet<>();

        List<List<Integer>> fullPermutationList = new ArrayList<>();
        fullPermutation(nums, 0, new ArrayList<>(), fullPermutationList);
        for (List<Integer> subArr : fullPermutationList) {
            if (subArr.size() == 3) {
                int arrSum = 0;
                for (Integer num : subArr) {
                    arrSum += num;
                }
                if (arrSum == 0) {
                    Collections.sort(subArr);
                    set.add(subArr);
                }
            }
        }
        ans.addAll(set);
        return ans;
    }

    /**
     * 子序列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subSequence(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                List<Integer> subList = new ArrayList<>();
                for (int k = i; k <= j; k++) {
                    subList.add(nums[k]);
                }
                ans.add(subList);
            }
        }
        return ans;
    }


    /**
     * 全排列流程
     *
     * @param nums
     * @param index
     * @param path
     * @return
     */
    public static void fullPermutation(int[] nums, int index, List<Integer> path, List<List<Integer>> ans) {
        if (index == nums.length) {
            ans.add(path);
        } else {
            System.out.println(index);
            List<Integer> selected = new ArrayList<>(nums.length);
            selected.addAll(path);
            selected.add(nums[index]);
            fullPermutation(nums, index + 1, selected, ans);

            List<Integer> noSelected = new ArrayList<>(nums.length);
            noSelected.addAll(path);
            fullPermutation(nums, index + 1, noSelected, ans);
        }
    }

    private static int count = 0;

    /**
     * 全排列流程
     *
     * @param nums
     * @param index
     * @param path
     * @return
     */
    public static void fullPermutation(int[] nums, int index, int[] path, int[][] ans) {
        if (index == nums.length) {
            ans[0] = path;
        } else {
            path[index] = nums[index];
            fullPermutation(nums, index + 1, path, ans);

            fullPermutation(nums, index + 1, path, ans);
        }
    }


    public static void main(String[] args) {
        int[] nums = {0, 0, 0};
        long starTime = System.currentTimeMillis();
        System.out.println(threeSum2(nums));
        System.out.println(System.currentTimeMillis() - starTime);
    }
}
