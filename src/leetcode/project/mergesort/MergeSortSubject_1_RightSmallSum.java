package leetcode.project.mergesort;

public class MergeSortSubject_1_RightSmallSum {

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
        int p1 = M;
        int p2 = R;
        int index = help.length - 1;
        while (p1 >= L && p2 >= M + 1) {
            //  1,2,2,3,3,6     1,1,1,1,1,2,6,6,7
            if (nums[p1] > nums[p2]) {
                for (int i = p2; i > M; i--) {
                    ans += nums[i];
                }
            }
            help[index--] = nums[p1] <= nums[p2] ? nums[p2--] : nums[p1--];
        }

        while (p1 >= L) {
            help[index--] = nums[p1--];
        }

        while (p2 >= M + 1) {
            help[index--] = nums[p2--];
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
