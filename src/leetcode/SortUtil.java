package leetcode;

import java.util.List;

public class SortUtil {

    public static void reverse(int[] nums, int L, int R) {
        while (L <= R) {
            swap(nums, L++, R--);
        }
    }

    public static void swap(int[] nums, int L, int R) {
        int tmp = nums[R];
        nums[R] = nums[L];
        nums[L] = tmp;
    }

    public static void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void print(List<List<Integer>> nums) {
        for (List<Integer> subList : nums) {
            for (Integer num : subList) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
