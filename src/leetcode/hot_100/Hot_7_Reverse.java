package leetcode.hot_100;

/**
 * 给你一个 32 位的有符号整数 x, 返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31 − 1], 就返回 0.
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 */
public class Hot_7_Reverse {

    /**
     * 核心流程
     * x % 10
     * x / 10
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int maxValue = Integer.MAX_VALUE;
        int minValue = Integer.MIN_VALUE;
        if (x == 0 || x > maxValue || x < minValue) {
            return 0;
        }
        int ans = 0;
        while (x != 0) {
            //  获取最后一位整数
            int pop = x % 10;
            //  原始数值不断缩小
            x = x / 10;

            if (ans > maxValue / 10 || (ans == maxValue / 10 && pop > 7)) {
                return 0;
            }
            if (ans < minValue / 10 || (ans == minValue / 10 && pop < -8)) {
                return 0;
            }
            //  目标值不断增大
            ans = 10 * ans + pop;
        }
        return ans;
    }

    public static void main(String[] args) {
        int x = -123;
        System.out.println(reverse(1534236));
    }
}
