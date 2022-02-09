package base.recursive.classic.dp;

public class 最短路径问题 {

    public static int minSum(int[][] matrix) {
        return f(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    public static int s(int[][] grid, int x, int y) {
        if (x < 0 || y < 0) {
            return -1;
        }
        if (x == 0 && y == 0) {
            return grid[x][y];
        }
        int left = s(grid, x - 1, y);
        int up = s(grid, x, y - 1);
        if (left == -1) {
            return grid[x][y] + up;
        }
        if (up == -1) {
            return grid[x][y] + left;
        }
        return grid[x][y] + Math.min(left, up);

    }

    /**
     * 暴力尝试, f的含义是:从 x, y出发, 到目标a, b的最短路径
     *
     * @param matrix
     * @param sourceX
     * @param sourceY
     * @param targetX
     * @param targetY
     * @return
     */
    public static int f(int[][] matrix, int sourceX, int sourceY, int targetX, int targetY) {
        //  base case 越界情况
        if (sourceX > targetX || sourceY > targetY || sourceX < 0 || sourceY < 0) {
            return -1;
        }
        //  base case 走到终点情况, 终点的步数不参与计算(根据题意进行动态返回)
        if (sourceX == targetX && sourceY == targetY) {
            return matrix[sourceX][sourceY];
        }

        //  普遍情况如下
        int curStep = matrix[sourceX][sourceY];
        int right = f(matrix, sourceX + 1, sourceY, targetX, targetY);
        int down = f(matrix, sourceX, sourceY + 1, targetX, targetY);
        if (right == -1) {
            return down + curStep;
        }
        if (down == -1) {
            return right + curStep;
        }
        //  终点前一个位置, 直接返回
        if (right == 0 || down == 0) {
            return curStep;
        }
        return curStep + Math.min(right, down);
    }

    /**
     * dp
     *
     * @param matrix
     * @return
     */
    public static int dp(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        dp[N - 1][M - 1] = matrix[N - 1][M - 1];
        //  row
        for (int row = N - 2; row >= 0; row--) {
            dp[row][M - 1] = dp[row + 1][M - 1] + matrix[row][M - 1];
        }
        //  col
        for (int col = M - 2; col >= 0; col--) {
            dp[N - 1][col] = dp[N - 1][col + 1] + matrix[N - 1][col];
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = M - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) + matrix[i][j];
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5}, {2, 7, 1}, {4, 9, 2}};
        System.out.println(minSum(matrix));
        System.out.println(dp(matrix));
        System.out.println(s(matrix, matrix.length - 1, matrix[0].length - 1));
    }
}
