package leetcode.daily.easy;

/**
 * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
 * <p>
 * 战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造
 * 其中 k 可以是任意大小。两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
 * <p>
 * 链接：https://leetcode-cn.com/problems/battleships-in-a-board
 */
public class CountBattleships_419 {

    public static int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 'X') continue;
                board[i][j] = '-';
                //  下位置存在X 设置为 -
                for (int k = i + 1; k < m && board[k][j] == 'X'; k++) {
                    board[k][j] = '-';
                }
                //  右位置存在X 设置为 -
                for (int k = j + 1; k < n && board[i][k] == 'X'; k++) {
                    board[i][k] = '-';
                }
                ans++;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '-') board[i][j] = 'X';
            }
        }
        return ans;
    }

    public static int DFS(int[][] lrtb, boolean[][] dp, char[][] board, int MIndex, int NIndex, int ans) {
        boolean flag = true;
        for (int i = 0; i < lrtb.length; i++) {
            int M = lrtb[i][0] + MIndex;
            int N = lrtb[i][1] + NIndex;
            if (N < 0 || N == board[0].length || M < 0 || M == board.length || board[M][N] == 'X') {
                flag = false;
            }
        }
        if (flag) {
            ans += 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        char[][] board = {{'X', '.', 'X', '.'}, {'.', 'X', '.', 'X'}, {'X', '.', 'X', '.'}};
        System.out.println(countBattleships(board));
    }
}
