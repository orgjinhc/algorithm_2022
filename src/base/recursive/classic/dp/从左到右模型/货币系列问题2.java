package base.recursive.classic.dp.从左到右模型;

/**
 * 每个位置的货币可以使用多次
 */
public class 货币系列问题2 {

    public static void main(String[] args) {
        int[] arr = {1, 1, 1};
        System.out.println(minPathSum(arr, 2));
        System.out.println(minPathSumByDp(arr, 2));
    }

    public static int minPathSum(int[] arr, int aim) {
        return f(arr, 0, aim);
    }


    public static int minPathSumByDp(int[] arr, int aim) {
        return dp(arr, aim);
    }


    /**
     * arr[index...], 组成rest这么多钱, 有几种方法
     *
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    public static int f(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += f(arr, index + 1, rest - (zhang * arr[index]));
        }
        return ways;
    }

    /**
     * arr[index...], 组成rest这么多钱, 有几种方法
     *
     * @param arr
     * @param rest
     * @return
     */
    public static int dp(int[] arr, int rest) {
        int N = arr.length;
        int[][] dp = new int[N + 1][rest + 1];
        dp[N][0] = 1;
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= rest; j++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[i] <= rest; zhang++) {
                    ways += dp[i + 1][rest - (zhang * arr[i])];
                }
                dp[i][j] = ways;
            }
        }
        return dp[0][rest];
    }
}
