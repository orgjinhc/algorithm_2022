package base.recursive.classic.dp;

/**
 * 每个位置的货币最多可以使用一次
 */
public class 货币系列问题从左向右模型1 {

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
        //  不是一种有效策略
        if (rest < 0) {
            return 0;
        }
        //  没钱了
        if (index == arr.length) {
            //  rest没了, 正好是一种组合策略
            return rest == 0 ? 1 : 0;
        }
        int noSelected = f(arr, index + 1, rest);
        int selected = f(arr, index + 1, rest - arr[index]);
        return noSelected + selected;
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
                dp[i][j] = dp[i + 1][j] + (j - arr[i] >= 0 ? dp[i + 1][j - arr[i]] : 0);
            }
        }
        return dp[0][rest];
    }
}
