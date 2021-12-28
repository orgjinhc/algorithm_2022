package leetcode.hot_100;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * 链接：https://leetcode-cn.com/problems/unique-paths
 */
public class Hot_62_UniquePaths {

    /**
     * 输入：m = 3, n = 2
     * 输出：3
     * 解释：
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向下 -> 向下
     * 2. 向下 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向下
     * <p>
     * 输入：m = 7, n = 3
     * 输出：28
     * <p>
     * 输入：m = 3, n = 3
     * 输出：6
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        //  动态规划
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
//        //  需要的步数
//        long sum = m + n - 2;
//        //  m - 1 向下最大距离
//        //  n - 1 向右最大距离
//        int min = Math.min(m - 1, n - 1);
//        long ret = 1;
//        long ins = 1;
//        for (int i = 0; i < min; i++) {
//            ret *= sum - i;
//            ins *= min - i;
//        }
//        return (int) (ret / ins);
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 7));
    }
}
