package leetcode.project.mergesort;

public class MergeSortSubject_1_LeftSmallSum {

    public static int minSum(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1, 0);
    }

    public static int mergeSort(int[] nums, int L, int R, int ans) {
        if (L == R) {
            return 0;
        }
        int M = (L + R) / 2;
        return mergeSort(nums, L, M, ans) +
                mergeSort(nums, M + 1, R, ans) +
                merge(nums, L, M, R);
    }

    public static int merge(int[] nums, int L, int M, int R) {
        int ans = 0;
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int index = 0;
        while (p1 <= M && p2 <= R) {
            ans += nums[p1] < nums[p2] ? (R - p2 + 1) * nums[p1] : 0 * nums[p1];

            //  与传统mergeSort的区别就是稳定性差别 nums[p1] < nums[p2]
            help[index++] = nums[p1] < nums[p2] ? nums[p1++] : nums[p2++];
        }

        while (p1 <= M) {
            help[index++] = nums[p1++];
        }

        while (p2 <= R) {
            help[index++] = nums[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            nums[L + i] = help[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 6};
        System.out.println(minSum(nums));
    }
}
