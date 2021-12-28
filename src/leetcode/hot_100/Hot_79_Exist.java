package leetcode.hot_100;

/**
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-search
 */
public class Hot_79_Exist {

    /**
     * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
     * 输出：true
     *
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        if (word == null || word.equals("")) {
            return true;
        }
        if (board == null || board.length < 1) {
            return false;
        }
        int M = board.length;
        int N = board[0].length;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (f(board, i, j, word.toCharArray(), 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * DFS实现查询字符串是否在matrix内
     *
     * @param board
     * @param row
     * @param col
     * @param ans
     * @param k
     */
    private static boolean f(char[][] board, int row, int col, char[] ans, int k) {
        //  设置边界条件
        //  case1:如果k的长度等于ans的长度, 则认为找到了答案, 或已经找到满足要求的字符串
        if (k == ans.length) {
            return true;
        }

        //  case2:越界情况, 如果row or col 已经越界返回false, 认为没有答案
        if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1) {
            return false;
        }

        //  case3:没有找到答案返回false
        if (board[row][col] != ans[k]) {
            return false;
        }

        //  case4:当前位置字符和ans k位置的字符相同, 继续从当前位置的上下左右四个方向继续寻找下一个字符
        //  设置现场, 防止走回头路
        char tmp = board[row][col];
        board[row][col] = 0;
        //  DFS流程
        boolean find = f(board, row + 1, col, ans, k + 1) || f(board, row - 1, col, ans, k + 1)
                || f(board, row, col + 1, ans, k + 1) || f(board, row, col - 1, ans, k + 1);

        //  最后恢复现场
        board[row][col] = tmp;
        return find;
    }


    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String s = "ABCCED";
        System.out.println(exist(board, s));
    }
}
