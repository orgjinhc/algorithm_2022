package leetcode.daily.easy;

/**
 * 统计所有小于非负整数n的质数的数量。
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7
 * <p>
 * 输入：n = 0
 * 输出：0
 * <p>
 * 输入：n = 1
 * 输出：0
 * 链接：https://leetcode-cn.com/problems/count-primes
 */
public class CountPrimes_204 {

    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        int count = n / 2;
        boolean[] dp = new boolean[n];
        for (int i = 3; i * i < n; i += 2) {
            //  i 已经算过 不需要重复计算
            if (dp[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += 2 * i) {
                //  发现素数, 设置已经标识位, 总量--
                if (!dp[j]) {
                    dp[j] = true;
                    count--;
                }
            }
        }
        return count;
    }
}
