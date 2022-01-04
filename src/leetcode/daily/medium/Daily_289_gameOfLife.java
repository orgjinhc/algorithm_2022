package leetcode.daily.medium;

public class Daily_289_gameOfLife {
    public void gameOfLife(int[][] board) {
        int[][] dp = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int ans = neighbors(board, i, j);
                if (ans == 3 || (ans == 2 && board[i][j] == 1)) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = dp[i][j];
            }
        }
    }

    public static int neighbors(int[][] board, int i, int j) {
        return f(board, i + 1, j)
                + f(board, i - 1, j)
                + f(board, i, j + 1)
                + f(board, i, j - 1)
                + f(board, i - 1, j - 1)
                + f(board, i - 1, j + 1)
                + f(board, i + 1, j - 1)
                + f(board, i + 1, j + 1);
    }

    public static int f(int[][] board, int i, int j) {
        return (i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] == 1) ? 1 : 0;
    }
}