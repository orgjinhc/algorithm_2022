package base.recursive.classic.dp;

public class LongestPalindromeSubseq {


    public int longestPalindromeSubseq(String s) {
        return f(s, 0, s.length() - 1);
    }

    /**
     * 递归含义:s 在 L...R 范围内最长回文子序列
     *
     * @param s
     * @param L
     * @param R
     * @return
     */
    public static int f(String s, int L, int R) {
        if (L == R) {
            return 1;
        }

        if (L + 1 == R) {
            return s.charAt(L) == s.charAt(R) ? 2 : 1;
        }

        int p1 = f(s, L + 1, R - 1);
        int p2 = f(s, L, R - 1);
        int p3 = f(s, L + 1, R);
        int p4 = s.charAt(L) == s.charAt(R) ? 2 + f(s, L + 1, R - 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }


    public static int dp(String s) {
        int N = s.length();
        int[][] dp = new int[N ][N ];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
            if (i < N - 1) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;
            }
        }
        //  4 * 4 矩阵画一下
        //  从下往上:对角线(N-1) 和 对角线 - 1 (N-2), 两个位置
        for (int L = N - 3; L >= 0; L--) {
            //  从左往右:对角线(L = R), 对角线 + 1(L + 1), 两个位置
            for (int R = L + 2; R < N; R++) {
                int p1 = dp[L + 1][R - 1];
                int p2 = dp[L][R - 1];
                int p3 = dp[L + 1][R];
                int p4 = s.charAt(L) == s.charAt(R) ? 2 + dp[L + 1][R - 1] : 0;
                dp[L][R] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }

        return dp[0][N - 1];
    }
}
