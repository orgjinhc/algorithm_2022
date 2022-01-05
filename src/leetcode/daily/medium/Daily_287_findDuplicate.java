package leetcode.daily.medium;

public class Daily_287_findDuplicate {

    public static int findDuplicate(int[] nums) {
        int S = nums[0];
        int F = nums[nums[0]];

        while (S != F) {
            S = nums[S];
            F = nums[nums[F]];
        }

        F = 0;
        while (S != F) {
            S = nums[S];
            F = nums[F];
        }
        return S;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(findDuplicate(nums));
    }
}