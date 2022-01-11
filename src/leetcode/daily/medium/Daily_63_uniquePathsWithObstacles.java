package leetcode.daily.medium;

public class Daily_63_uniquePathsWithObstacles {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        int[][] f = new int[n][m];
        f[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;

        for (int i = 0; i < m; ++i) {
            if (obstacleGrid[i][0] == 0) {
                f[i][0] = 1;
            }
        }
        for (int j = 0; j < n; ++j) {
            if (obstacleGrid[0][j] == 0) {
                f[0][j] = 1;
            }
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }

        return f[m - 1][n - 1];
    }


    public static void main(String[] args) {
        System.out.println(uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 0, 0}}));
    }
}
