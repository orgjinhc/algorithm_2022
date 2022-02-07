package leetcode.everyday.january;

public class January_22_removePalindromeSub {

    public static int removePalindromeSub(String s) {
        if (s == null || s.length() < 1) {
            return 1;
        }
        int L = 0;
        int R = s.length() - 1;
        while (L < R) {
            if (s.charAt(L) != s.charAt(R)) {
                return 2;
            }
            L++;
            R--;
        }
        return 1;
    }

    public static void main(String[] args) {
        String s = "baabb";
//        System.out.println(removePalindromeSub(s));

        int[] nums = {2,2,1,9};
        int target = 10;
        System.out.println(purchasePlans(nums, target));
    }


    public static int purchasePlans(int[] nums, int target) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] <= target) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
