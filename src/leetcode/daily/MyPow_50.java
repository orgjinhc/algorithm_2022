package leetcode.daily;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * https://leetcode-cn.com/problems/powx-n/
 */
public class MyPow_50 {


    /**
     * 输入：x = 2.00000, n = 10
     * 输出：1024.00000
     * 示例 2：
     * <p>
     * 输入：x = 2.10000, n = 3
     * 输出：9.26100
     * 示例 3：
     * <p>
     * 输入：x = 2.00000, n = -2
     * 输出：0.25000
     * 解释：2-2 = 1/22 = 1/4 = 0.25
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        double ans = 1;
        double t = x;
        //  转换n为正数, 存在越界情况, 先将最小值转化为最大值, 丢失的一位将会在最后补上
        int tmpN = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
        //  10 ~= 00000000 00000000 00000000 00001010
        while (tmpN != 0) {
            //  0 & 1 = 0
            //  1 & 1 = 1
            if ((tmpN & 1) != 0) {
                ans *= t;
            }
            //  00001010 ～= t1
            //  00000101 ～= t2  答案
            //  00000010 ～= t3
            //  00000001 ～= t4  答案
            t *= t;
            //  00000000 00000000 00000000 00001010
            //  右移1位后, 如下
            //  00000000 00000000 00000000 00000101
            tmpN >>= 1;
        }
        if (n == Integer.MIN_VALUE) {
            ans *= ans;
        }
        return n < 0 ? 1 / ans : ans;
    }

    public static void main(String[] args) {
        double x = -13.62608;
        int n = 3;
        System.out.println(myPow(x, n));
    }
}
