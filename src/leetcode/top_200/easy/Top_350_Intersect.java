package leetcode.top_200.easy;

import leetcode.util.LCUtil;

import java.util.*;

/**
 * 给你两个整数数组nums1 和 nums2 ，请你以数组形式返回两数组的交集。
 * 返回结果中每个元素出现的次数，应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 * <p>
 * 进阶：
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果nums1的大小比nums2 小，哪种方法更优？
 * 如果nums2的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * <p>
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
 */
public class Top_350_Intersect {

    public static int[] intersect1(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int M = nums2.length;
        int L1 = 0;
        int L2 = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> ans = new ArrayList<>();
        while (L1 < N && L2 < M) {
            if (nums1[L1] == nums2[L2]) {
                ans.add(nums1[L1]);
                if (N == M) {
                    L1++;
                    L2++;
                } else {
                    int i = N > M ? L2++ : L1++;
                }
            }
            if (N == M) {
                L1++;
                L2++;
            } else {
                int i = N > M ? L1++ : L2++;
            }
        }

        int[] dp = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            dp[i] = ans.get(i);
        }
        return dp;
    }


    /**
     * Hash算法实现
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersectByMap(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] F = nums1.length > nums2.length ? nums1 : nums2;
        int[] S = F == nums1 ? nums2 : nums1;

        for (int num : S) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int[] ans = new int[S.length];
        int index = 0;
        for (int num : F) {
            if (map.containsKey(num) && map.get(num) > 0) {
                ans[index++] = num;
                map.put(num, map.get(num) - 1);
            }
        }
        //  复制
        return Arrays.copyOfRange(ans, 0, index);
    }

    /**
     * 双指针实现
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersectDoubleIndex(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int L1 = 0;
        int L2 = 0;
        int ansIndex = 0;
        int[] ans = new int[Math.min(nums1.length, nums2.length)];
        while (L1 < nums1.length && L2 < nums2.length) {
            //  L1位置的数大于L2位置的数, 小数右移指针, 因为两个数组内的元素已经排序完成
            if (nums1[L1] > nums2[L2]) {
                L2++;
            } else if (nums1[L1] < nums2[L2]) {
                L1++;
            } else {
                //  两个数组内的元素相同, 视为交集逻辑
                //  收集答案
                ans[ansIndex] = nums1[L1];
                //  移动指针
                L1++;
                L2++;
                ansIndex++;
            }
        }
        //  复制
        return Arrays.copyOfRange(ans, 0, ansIndex);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {1, 1};
        LCUtil.print(intersectDoubleIndex(nums1, nums2));
    }
}