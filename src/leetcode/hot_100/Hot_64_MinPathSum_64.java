package leetcode.hot_100;

/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 */
public class Hot_64_MinPathSum_64 {

    /**
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 200
     * 0 <= grid[i][j] <= 100
     *
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;

        int[][] dp = new int[M][N];
        //  0行 0列位置赋值grid的 0行 0列
        dp[0][0] = grid[0][0];

        //  row -> 0 col 路径前缀和
        //       |----------------| --- |----------------|
        //  grid |1 2 3 4 5 6 7 8 | --- |1 3 6 x x x x x | dp
        //       |x x x x x x x x | --- |x x x x x x x x |
        //       |----------------| --- |----------------|
        for (int col = 1; col < N; col++) {
            dp[0][col] = dp[0][col - 1] + grid[0][col];
        }

        //  col -> 0 row 路径前缀和
        //       |----------------| --- |----------------|
        //  grid |1 2 3 4 5 6 7 8 | --- |1 x x x x x x x | dp
        //       |2 x x x x x x x | --- |3 x x x x x x x |
        //       |----------------| --- |----------------|
        for (int row = 1; row < M; row++) {
            dp[row][0] = dp[row - 1][0] + grid[row][0];
        }

        //  row col 余下的所有空位置都从左、上位置获取最小路径 + 当前位置的路径 作为结果封装到 dp[row][col]
        //       |----------------| --- |-----------------|
        //  grid |1 2 3 4 5 6 7 8 | --- | 1 3 6 x x x x x | dp
        //       |2 x x x x x x x | --- | 3 x x x x x x x |
        //       |3 x x x x x x x | --- | 6 x x x x x x x |
        //       |4 x x x x x x x | --- |10 x x x x x x x |
        //       |----------------| --- |-----------------|
        for (int row = 1; row < M; row++) {
            for (int col = 1; col < N; col++) {
                dp[row][col] += grid[row][col] + Math.min(dp[row - 1][col], dp[row][col - 1]);
            }
        }
        return dp[M - 1][N - 1];
    }


    public static void main(String[] args) {
        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }
}
