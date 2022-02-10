package base.recursive.classic.dp;

/**
 * 从左往右递归模型
 */
public class 背包问题从左往右模型 {

    public static void main(String[] args) {
        int[] weights = {5000, 1000, 500, 100};
        int[] values = {120, 40, 12, 3};
        int bag = 200;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

    /**
     * 暴力尝试解
     *
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }

    /**
     * @param w     重量
     * @param v     价值
     * @param index 当前来到index位置, 0 ~ index位置已经做过选择, 继续尝试 index ~ w.length 位置
     * @param rest  当前背包剩余空间
     * @return
     */
    public static int process(int[] w, int[] v, int index, int rest) {
        //  无空间
        if (rest < 0) {
            return -1;
        }
        //  无货
        if (index == w.length) {
            return 0;
        }
        //  有货有空间，不计算当前货物价值，继续往下递归
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        //  有货有空间，计算当前货物价值，继续往下递归
        //  先尝试下个位置的货物是否能放进背包内, 如果满足条件再放入背包
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            //  有货有空间，计算当前货物价值
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }


    /**
     * 动态规划
     *
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int dp(int[] w, int[] v, int bag) {
        int N = w.length;
        //  [0]index = 0 ~ N  -> N + 1 大小
        //  [1]rest = 0 ~ rest -> rest + 1 大小
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                //  判断下一个位置是否可以放入背包
                if (rest - w[index] >= 0) {
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }
}
