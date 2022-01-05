package leetcode.daily.easy;

/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * <p>
 * 提示'n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
 */
public class TrailingZeroes_172 {

    public int trailingZeroes(int n) {
        int ans = 0;
        while (n != 0) {
            n = n / 5;
            ans = ans + n;
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
