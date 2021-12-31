package leetcode.top_200.medium;

/**
 * 给你两个整数 a 和 b ，不使用 运算符+ 和-，计算并返回两整数之和。
 * <p>
 * 示例 1：
 * <p>
 * 输入：a = 1, b = 2
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：a = 2, b = 3
 * 输出：5
 * <p>
 * 提示：
 * <p>
 * -1000 <= a, b <= 1000
 * <p>
 * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
 */
public class Top_371_GetSum {

    public static int getSum(int a, int b) {
        int sum = a;
        while (b != 0) {
            //  1的位置组合到一起,
            sum = a ^ b;
            //  都是1的位置左移1位
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(getSum(5, 0));
    }
}
