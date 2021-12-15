package leetcode.daily;

/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * https://leetcode-cn.com/problems/surrounded-regions/
 */
public class Solve_130 {

    /**
     * 输入：board = [['X','X','X','X'],['X','O','O','X'],['X','X','O','X'],['X','O','X','X']]
     * 输出：[['X','X','X','X'],['X','X','X','X'],['X','X','X','X'],['X','O','X','X']]
     * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     *
     * @param board
     */
    public static void solve(char[][] board) {
        if (null == board || board.length == 1 || board[0].length == 1) {
            return;
        }
        int M = board.length;
        int N = board[0].length;
        //  边界处理:左右边界
        for (int i = 0; i < M; i++) {
            if (board[i][0] == 'O') {
                infect(board, i, 0);
            }

            if (board[i][N - 1] == 'O') {
                infect(board, i, N - 1);
            }
        }
        //  边界处理:上下边界
        for (int j = 1; j < N - 1; j++) {
            if (board[0][j] == 'O') {
                infect(board, 0, j);
            }

            if (board[M - 1][j] == 'O') {
                infect(board, M - 1, j);
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }

                if (board[i][j] == 'Y') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * 感染函数
     * 感染满足条件的上、下、左、右四个方向的邻居
     *
     * @param board
     * @param m
     * @param n
     */
    public static void infect(char[][] board, int m, int n) {
        if (m < 0 || m == board.length || n < 0 || n == board[0].length || board[m][n] != 'O') {
            return;
        }
        board[m][n] = 'Y';
        infect(board, m + 1, n);
        infect(board, m - 1, n);
        infect(board, m, n + 1);
        infect(board, m, n - 1);
    }

    public static void main(String[] args) {
        char[][] board = {{'X', 'O', 'X'}, {'O', 'X', 'O'}, {'X', 'O', 'X'}};
        solve(board);
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
