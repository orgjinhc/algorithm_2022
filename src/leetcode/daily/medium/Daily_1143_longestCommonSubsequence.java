package leetcode.daily.medium;

public class Daily_1143_longestCommonSubsequence {

    public static void main(String[] args) {
        String text1 = "abc", text2 = "def";
        System.out.println(longestCommonSubsequence(text1, text2));
    }

    public static int longestCommonSubsequence(String text1, String text2) {
        return process(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    /**
     * 递归函数, 返回 0...index 上的最长公共子序列
     *
     * @param s1      字符串1
     * @param s2      字符串2
     * @param s1Index 0...s1Index 位置上最长公共子序列是多长
     * @param s2Index 0...s2Index 位置上最长公共子序列是多长
     * @return
     */
    public static int process(String s1, String s2, int s1Index, int s2Index) {
        char s1char = s1.charAt(s1Index);
        char s2char = s2.charAt(s2Index);
        //  s1[0], s2[0] 位置上求最大公共子序列
        if (s1Index == 0 && s2Index == 0) {
            return s1char == s2char ? 1 : 0;
        } else if (s1Index == 0) {
            //  s1[0], s2[0...s2Index] 位置上求最大公共子序列
            if (s1char == s2char) {
                return 1;
            } else {
                return process(s1, s2, s1Index, s2Index - 1);
            }
        } else if (s2Index == 0) {
            //  s1[0...s1Index], s2[0] 位置上求最大公共子序列
            if (s1char == s2char) {
                return 1;
            } else {
                return process(s1, s2, s1Index - 1, s2Index);
            }
        } else {
            //  s1[0...s1Index], s2[0...s2Index] 位置上求最大公共子序列
            //  第1种情况, s1Index不可能, s2Index可能
            int p1 = process(s1, s2, s1Index - 1, s2Index);
            //  第2种情况, s1Index可能, s2Index不可能
            int p2 = process(s1, s2, s1Index, s2Index - 1);
            //  第3种情况, s1Index可能, s2Index可能
            int p3 = s1char == s2char ? (1 + process(s1, s2, s1Index - 1, s2Index - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public static int longestCommonSubsequence2(String text1, String text2) {
        int N = text1.length();
        int M = text2.length();

        int[][] dp = new int[N][M];
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        //  fill first col
        for (int col = 1; col < M; col++) {
            dp[0][col] = text1.charAt(0) == text2.charAt(col) ? 1 : dp[0][col - 1];
        }
        //  fill first row
        for (int row = 1; row < N; row++) {
            dp[row][0] = text1.charAt(row) == text2.charAt(0) ? 1 : dp[row - 1][0];
        }
        //  fill other row col
        for (int row = 1; row < N; row++) {
            for (int col = 1; col < M; col++) {
                int p1 = dp[row - 1][col];
                int p2 = dp[row][col - 1];
                int p3 = text1.charAt(row) == text2.charAt(col) ? (1 + dp[row - 1][col - 1]) : 0;
                dp[row][col] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }
}
