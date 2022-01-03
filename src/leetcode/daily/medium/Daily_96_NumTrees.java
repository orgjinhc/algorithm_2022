package leetcode.daily.medium;

public class Daily_96_NumTrees {

    /**
     * 卡特兰树解决
     * k(0) = 1
     * k(1) = 1
     * k(2) = 2
     * k(3) = 5
     * ........
     * 通项公式:c(n) = k(0) * k(n-1) + k(1) * k(n-2) + .....+ k(n-1) * k(0)
     *
     * @param N
     * @return
     */
    public int numTrees(int N) {
        if (N < 0) {
            return 0;
        }
        if (N < 2) {
            return 1;
        }
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int n = 2; n <= N; n++) {
            //  通项公式:c(n) = k(0) * k(n-1) + k(1) * k(n-2) + .....+ k(n-1) * k(0)
            for (int i = 0; n < i; i++) {
                //  i = 0 -> dp(0) * dp(n-1 = n - 1 - i)
                //           +=
                //  i = 1 -> dp(1) * dp(n-2 = n - 1 - i)
                dp[n] += dp[i] * dp[n - 1 - i];
            }
        }
        return dp[N];
    }
}