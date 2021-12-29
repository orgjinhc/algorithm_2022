package leetcode.top_200.easy;

public class Tio_13_RomanToInteger {

    public static int romanToInt(String s) {
        //  用于收集 原始字符串对应的映射信息
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            //  按规律收集即可
            switch (s.charAt(i)) {
                case 'M':
                    dp[i] = 1000;
                    break;
                case 'D':
                    dp[i] = 500;
                    break;
                case 'C':
                    dp[i] = 100;
                    break;
                case 'L':
                    dp[i] = 50;
                    break;
                case 'X':
                    dp[i] = 10;
                    break;
                case 'V':
                    dp[i] = 5;
                    break;
                case 'I':
                    dp[i] = 1;
                    break;
            }
        }
        int ans = 0;
        //  两两比较, 判断是否是相加逻辑还是相减逻辑
        for (int i = 0; i < dp.length - 1; i++) {
            //  当前数小于下一位置数, 相减逻辑
            if (dp[i] < dp[i + 1]) {
                ans -= dp[i];
            } else {
                //  当前数不小于下一位置数, 相加逻辑
                ans += dp[i];
            }
        }
        //  最后一位没有参与运算, 直接相加即可
        return ans + dp[dp.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("MCMXCIV"));
    }
}
