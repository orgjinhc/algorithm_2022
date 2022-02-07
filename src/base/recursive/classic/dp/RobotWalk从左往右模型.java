package base.recursive.classic.dp;

public class RobotWalk从左往右模型 {

    public static void main(String[] args) {
        System.out.println(robotWalk1(7, 2, 4, 4));
        System.out.println(robotWalk2(7, 2, 4, 4));
        System.out.println(robotWalk3(7, 2, 4, 4));
    }

    /**
     * 在 N(1 ~ N) 的范围内, 从 start 位置处开始移动, 移动K步后到达 aim位置, 返回总方法数
     * robot移动规则如下:
     * 1.如果在最左边, 只能向右移动
     * 2.如果在最右边, 只能向左移动
     * 3.如果在不是最左、最右的情况, 可以向两边移动
     * <p>
     * 解法:
     * 根据移动规则进行递归设计, base case是关键:如果没有移动步数了, 判断当前移动的位置是否在目标位置, 如果在就有一种解法, 如果不在就没有解法
     *
     * @param N     总长度
     * @param start 开始位置
     * @param aim   目标位置
     * @param K     移动步数
     * @return
     */
    public static int robotWalk1(int N, int start, int aim, int K) {
        return f1(K, start, aim, N);
    }


    /**
     * 递归含义
     *
     * @param rest 剩余步数
     * @param cur  当前机器人所在位置
     * @param aim  机器人目标位置
     * @param n    总长度
     * @return
     */
    public static int f1(int rest, int cur, int aim, int n) {
        //  base case 的设计, 如果 rest 为0, 判断 cur 与 aim 关系
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }

        //  下面的逻辑按照机器人的移动规则实现
        if (cur == 1) {
            return f1(rest - 1, cur + 1, aim, n);
        }
        if (cur == n) {
            return f1(rest - 1, cur - 1, aim, n);
        }
        return f1(rest - 1, cur + 1, aim, n) + f1(rest - 1, cur - 1, aim, n);
    }


    /**
     * 在 N(1 ~ N) 的范围内, 从 start 位置处开始移动, 移动K步后到达 aim位置, 返回总方法数
     * robot移动规则如下:
     * 1.如果在最左边, 只能向右移动
     * 2.如果在最右边, 只能向左移动
     * 3.如果在不是最左、最右的情况, 可以向两边移动
     * <p>
     * 解法:
     * 根据移动规则进行递归设计, base case是关键:如果没有移动步数了, 判断当前移动的位置是否在目标位置, 如果在就有一种解法, 如果不在就没有解法
     *
     * @param N     总长度
     * @param start 开始位置
     * @param aim   目标位置
     * @param K     移动步数
     * @return
     */
    public static int robotWalk2(int N, int start, int aim, int K) {
        //  构造一个缓存表, 用于应对这种自顶向下(记忆化搜索)的重复计算场景
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        return f2(K, start, aim, N, dp);
    }

    /**
     * 递归含义
     *
     * @param rest 剩余步数
     * @param cur  当前机器人所在位置
     * @param aim  机器人目标位置
     * @param n    总长度
     * @param dp   缓存表, 用于加速搜索
     * @return
     */
    public static int f2(int rest, int cur, int aim, int n, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = f2(rest - 1, cur + 1, aim, n, dp);
        } else if (cur == n) {
            ans = f2(rest - 1, cur - 1, aim, n, dp);
        } else {
            ans = f2(rest - 1, cur + 1, aim, n, dp) + f2(rest - 1, cur - 1, aim, n, dp);
        }
        dp[cur][rest] = ans;
        return ans;
    }


    /**
     * 在 N(1 ~ N) 的范围内, 从 start 位置处开始移动, 移动K步后到达 aim位置, 返回总方法数
     * robot移动规则如下:
     * 1.如果在最左边, 只能向右移动
     * 2.如果在最右边, 只能向左移动
     * 3.如果在不是最左、最右的情况, 可以向两边移动
     * <p>
     * 解法:
     * 根据移动规则进行递归设计, base case是关键:如果没有移动步数了, 判断当前移动的位置是否在目标位置, 如果在就有一种解法, 如果不在就没有解法
     *
     * @param N     总长度
     * @param start 开始位置
     * @param aim   目标位置
     * @param K     移动步数
     * @return
     */
    public static int robotWalk3(int N, int start, int aim, int K) {
        //  构造一个缓存表, 用于应对这种自顶向下(记忆化搜索)的重复计算场景
        int[][] dp = new int[N + 1][K + 1];
        //  第一列的aim位置为1, 其余位置都是0
        dp[aim][0] = 1;
        for (int col = 1; col <= K; col++) {
            //  第一行
            dp[1][col] = dp[2][col - 1];
            //  2 ~ (N-1)
            for (int row = 1; row < N; row++) {
                dp[row][col] = dp[row - 1][col - 1] + dp[row + 1][col - 1];
            }
            //  最后一行
            dp[N][col] = dp[N - 1][col - 1];
        }
        return dp[start][K];
    }
}
