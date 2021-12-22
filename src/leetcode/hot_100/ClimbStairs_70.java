package leetcode.hot_100;

import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 */
public class ClimbStairs_70 {

    public static Map<Integer, Integer> dp = new HashMap<>();

    public static int climbStairs(int n) {
        return process(n);
    }

    public static int process(int n) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        if (n == 1 || n == 0) {
            return 1;
        }

        int ans = process(n - 1) + process(n - 2);
        dp.put(n, ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(45));
    }

}
