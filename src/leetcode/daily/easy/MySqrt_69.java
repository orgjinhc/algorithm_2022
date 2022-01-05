package leetcode.daily.easy;

/**
 * 给你一个非负整数 x ，计算并返回x的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/sqrtx
 */
public class MySqrt_69 {


    /**
     * 输入：x = 4
     * 输出：2
     * <p>
     * 输入：x = 8
     * 输出：2
     * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     * <p>
     * 整体思路：找到目标x的mid位置, 判断mid的平方是否小于x, 依次类推等价于找到小于x的最右位置
     *
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 3) {
            return 1;
        }
        long L = 0;
        long R = x;
        long M = 0;
        long ans = 0;
        while (L <= R) {
            M = (L + R) / 2;
            //  核心, 当前位置的数小于x, 记录并去右边区间继续二分
            if (M * M <= x) {
                ans = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }
}
