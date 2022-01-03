package leetcode.daily.easy;

public class Daily_680_ValidPalindrome {

    public static boolean validPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }
        int L = 0;
        int R = s.length() - 1;
        int[] ans = process(s, L, R);
        if (ans[0] >= ans[1]) {
            return true;
        }
        return isValidPalindrome(process(s, ans[0], ans[1] - 1), process(s, ans[0] + 1, ans[1]));
    }

    public static boolean isValidPalindrome(int[] ans1, int[] ans2) {
        return ans1[0] >= ans1[1] || ans2[0] >= ans2[1];
    }

    private static int[] process(String s, int L, int R) {
        while (L < R) {
            char leftChar = s.charAt(L);
            char rightChar = s.charAt(R);
            if (leftChar != rightChar) {
                return new int[]{L, R};
            }
            L++;
            R--;
        }
        return new int[]{L, R};
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(validPalindrome(s));
    }
}