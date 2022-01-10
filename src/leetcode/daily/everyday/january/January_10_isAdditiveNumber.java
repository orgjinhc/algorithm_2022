package leetcode.daily.everyday.january;

public class January_10_isAdditiveNumber {

    public static boolean additiveNumber(String num, int carry) {
        int[] dp = convert(num);
        int one = dp[0];
        for (int i = 0 + carry; i < dp.length; i += carry) {
            String two = "";
            for (int j = 0; j < carry; j++) {
                two = two + dp[i - 1];
            }
            if (dp[i] != one + two) {
                return false;
            }
            one = two;
        }
        return true;
    }

    public static boolean isAdditiveNumber(String num) {
        int[] dp = convert(num);
        int one = dp[0];
        for (int i = 2; i < dp.length; i++) {
            int two = dp[i - 1];
            if (dp[i] != one + two) {
                return false;
            }
            one = two;
        }
        return true;
    }

    public static void main(String[] args) {
        String num = "199100199";
        System.out.println(isAdditiveNumber(num));
    }

    private static int[] convert(String num) {
        String[] split = num.split("");
        int[] dp = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            dp[i] = Integer.parseInt(split[i]);
        }
        return dp;
    }
}
