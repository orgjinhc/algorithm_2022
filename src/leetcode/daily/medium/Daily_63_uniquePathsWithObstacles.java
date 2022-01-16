package leetcode.daily.medium;

public class Daily_63_uniquePathsWithObstacles {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[] dp = new int[m];
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (j >= 1 && obstacleGrid[i][j - 1] == 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[m - 1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}
        }));

        /**
         *  1, 1, 1
         *  1, 0, 0
         *  1, 1, 1
         */
    }
}
