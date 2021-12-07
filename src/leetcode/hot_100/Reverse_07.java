package leetcode.hot_100;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 */
public class Reverse_07 {

    /**
     * 核心流程
     * x % 10
     * x / 10
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        if (x == 0 || x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
            return 0;
        }
        int ans = 0;
        while (x != 0) {
            //  每次获取最后一位整数
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }

            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            ans = ans * 10 + pop;
            //  原始数值不断缩小
            x = x / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        int x = -123;
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1534236469);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(reverse(x));
    }
}
