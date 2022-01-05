package leetcode.daily.merge_sort_subject;

import java.util.ArrayList;
import java.util.List;

public class MergeSortSubject_1_MinSum {

    public static int minSum(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        mergeSort(nums, 0, nums.length - 1, ans);
        int sum = 0;
        for (Integer num : ans) {
            sum += num;
        }
        return sum;
    }

    public static void mergeSort(int[] nums, int L, int R, List<Integer> ans) {
        if (L == R) {
            return;
        }
        int M = (L + R) / 2;
        mergeSort(nums, L, M, ans);
        mergeSort(nums, M + 1, R, ans);
        ans.add(merge(nums, L, M, R));
    }

    public static int merge(int[] nums, int L, int M, int R) {
        int ans = 0;
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int index = 0;
        while (p1 <= M && p2 <= R) {
            if (nums[p2] > nums[p1]) {
                ans += (R - p2 + 1) * nums[p1];
            }
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
