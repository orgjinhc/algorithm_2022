package leetcode.top.hard;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 * <p>
 * 数独的解法需 遵循如下规则：
 * <p>
 * 数字1-9在每一行只能出现一次。
 * 数字1-9在每一列只能出现一次。
 * 数字1-9在每一个以粗实线分隔的3x3宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用'.'表示。
 * <p>
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 */
public class Top_37_SolveSudoku {

    /**
     * @param board
     */
    public static void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[board.length][board[0].length + 1];
        boolean[][] col = new boolean[board.length][board[0].length + 1];
        boolean[][] bucket = new boolean[board.length][board[0].length + 1];
        initMap(board, row, col, bucket);
        process(board, 0, 0, row, col, bucket);
    }

    /**
     * 当前来到 (i,j) 位置
     * 如果已经有数字, 跳到下一个位置上
     * 如果没有这个数字, 尝试1～9, 不能和 row、col、bucket 冲突
     *
     * @param board
     * @param i      位置
     * @param j      位置
     * @param row
     * @param col
     * @param bucket
     */
    private static boolean process(char[][] board, int i, int j, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        //  有效行号 0 ～ 8, i来到终止位置, 前面8行已经找到答案, 直接返回
        if (i == 9) {
            return true;
        }
        //  当离开 (i,j), 应该去的下一个位置
        //  核心思想, 每次都是向右移动, 移动到右边界后, 来到下一个行的最左位置
        int nextI = j != 8 ? i : i + 1;
        int nextJ = j != 8 ? j + 1 : 0;
        //  开始填数逻辑, 如果当前位置不需要填, 直接去下一个位置, 否则尝试 1～9
        if (board[i][j] != '.') {
            return process(board, nextI, nextJ, row, col, bucket);
        } else {
            //  先获取桶的位置
            int bucketNo = 3 * (i / 3) + (j / 3);
            for (int num = 1; num <= 9; num++) {
                //  num 如果之前出现过, 直接尝试下一个数字
                if (row[i][num] || col[j][num] || bucket[bucketNo][num]) {
                    continue;
                }
                //  尝试num
                row[i][num] = true;
                col[j][num] = true;
                bucket[bucketNo][num] = true;
                board[i][j] = (char) (num + '0');
                //  如果有一个分支尝试成功, 直接返回
                if (process(board, nextI, nextJ, row, col, bucket)) {
                    return true;
                }
                row[i][num] = false;
                col[j][num] = false;
                bucket[bucketNo][num] = false;
                board[i][j] = '.';
            }
            return false;
        }
    }

    public static void initMap(char[][] board, boolean[][] row, boolean[][] col, boolean[][] bucket) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int bucketNo = 3 * (i / 3) + (j / 3);
                int num = board[i][j] - '0';
                row[i][num] = true;
                col[j][num] = true;
                bucket[bucketNo][num] = true;
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        solveSudoku(board);
    }
}
