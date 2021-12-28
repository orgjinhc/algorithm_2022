package leetcode.top.medium;

/**
 * 请你判断一个9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * <p>
 * 注意：
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用'.'表示。
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-sudoku
 */
public class Top_36_IsValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        int M = board.length;
        int N = board[0].length;
        boolean[][] row = new boolean[M][N];
        boolean[][] col = new boolean[M][N];
        boolean[][] bucket = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                //  当前位置所在bucket
                int bucketNo = 3 * (i / 3) + (j / 3);
                System.out.println(board[i][j]);
                System.out.println(board[i][j] - '0');
                int num = board[i][j] - '0';

                if (row[i][num] || col[j][num] || bucket[bucketNo][num]) {
                    return false;
                }
                row[i][num] = true;
                col[j][num] = true;
                bucket[bucketNo][num] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'1','2'}
        };
        System.out.println(isValidSudoku(board));
    }
}
