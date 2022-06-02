package leetcode.hot_100;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1和nums2。
 * 请你找出并返回这两个正序数组的 中位数。
 * 算法的时间复杂度应该为 O(log (m+n))。
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 */
public class Hot_4_FindMedianSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] ans = merge(nums1, nums2);
        int N = ans.length;
        if (N % 2 == 0) {
            return (ans[N / 2 - 1] + ans[N / 2]) / 2.0;
        } else {
            return ans[N / 2];
        }
    }

    public static int[] merge(int[] nums1, int[] nums2) {
        int L1Length = nums1.length;
        int L2Length = nums2.length;
        int[] ans = new int[L1Length + L2Length];
        int L1 = 0;
        int L2 = 0;
        int index = 0;
        while (L1 < L1Length && L2 < L2Length) {
            ans[index++] = nums1[L1] < nums2[L2] ? nums1[L1++] : nums2[L2++];
        }

        while (L1 < L1Length) {
            ans[index++] = nums1[L1++];
        }

        while (L2 < L2Length) {
            ans[index++] = nums2[L2++];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {};
        int[] nums2 = {1};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
        System.out.println(medianSortedArrays);
    }
}
