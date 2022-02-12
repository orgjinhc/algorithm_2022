package leetcode.everyday.february;

public class February_12_numEnclaves {

    public static int numEnclaves(int[][] grid) {
        if (grid.length < 2 || grid[0].length < 2) {
            return 0;
        }

        int N = grid.length;
        int M = grid[0].length;

        for (int row = 0; row < N; row++) {
            infect(grid, row, 0);
            infect(grid, row, M - 1);
        }
        for (int col = 1; col < M; col++) {
            infect(grid, N - 1, col);
            infect(grid, 0, col);
        }
        int ans = 0;
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (grid[i][j] == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void infect(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 1) {
            return;
        }
        grid[x][y] = 0;
        infect(grid, x + 1, y);
        infect(grid, x - 1, y);
        infect(grid, x, y + 1);
        infect(grid, x, y - 1);
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {2, 4, 1}, {4, 6, 2}};
    }
}
