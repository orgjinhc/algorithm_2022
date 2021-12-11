package leetcode.daily;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 * https://leetcode-cn.com/problems/set-matrix-zeroes/
 */
public class SetZeroes_76 {

    /**
     * 利用辅助matrix实现
     *
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        if (null == matrix) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] dpMatrix = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //  如果一个位置是0, 行与列都设为0
                if (matrix[i][j] == 0) {
                    for (int col = 0; col < n; col++) {
                        dpMatrix[i][col] = true;
                    }
                    for (int row = 0; row < m; row++) {
                        dpMatrix[row][j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dpMatrix[i][j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 利用辅助matrix实现
     *
     * @param matrix
     */
    public static void setZeroes2(int[][] matrix) {
        if (null == matrix) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //  如果一个位置是0, 行与列都设为0
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes2(matrix);

        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
