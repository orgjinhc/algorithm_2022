package leetcode.daily.easy;

import java.util.PriorityQueue;

/**
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <p>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 */
public class Merge_88 {


    /**
     * 借助辅助数据结构(堆)实现
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            for (int i = 0; i < nums2.length; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }

        if (n == 0) {
            return;
        }

        int index = 0;
        PriorityQueue<Integer> ans = new PriorityQueue<>();
        while (m != index) {
            ans.add(nums1[index++]);
        }
        index = 0;
        while (n != index) {
            ans.add(nums2[index++]);
        }
        index = 0;
        while (!ans.isEmpty()) {
            nums1[index++] = ans.poll();
        }
    }

    /**
     * 借助双指针实现
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0 || n == 0) {
            for (int i = 0; i < nums2.length; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        //  归并后面的merge流程
        int index = nums1.length;
        while (n > 0 && m > 0) {
            //  相等情况向copy nums1, 尽早的腾出空间
            if (nums1[m] >= nums2[n]) {
                nums1[--index] = nums1[--m];
            } else {
                nums1[--index] = nums2[--n];
            }
        }


        while (m > 0) {
            nums1[--index] = nums1[--m];
        }

        while (n > 0) {
            nums1[--index] = nums2[--n];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        merge1(nums1, m, nums2, n);


        for (int i : nums1) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
