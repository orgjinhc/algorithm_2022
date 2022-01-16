package leetcode.daily.medium;


public class Daily_240_searchMatrix {

    public static boolean searchMatrix1(int[][] matrix, int target) {

        int M = matrix.length;
        int N = matrix[0].length;

        int index = 0;
        while (index < M && index < N) {
            if (binarySearch(matrix[index], target, index, N)) {
                return true;
            }

            for (int i = index; i < M; i++) {
                if (matrix[i][index] == target) {
                    return true;
                }
            }
            index++;
        }
        return false;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = binarySearchByRow(matrix, target);
        int col = binarySearchByCol(matrix, target);
        if (row == -1 && col == -1) {
            return matrix[0][0] == target;
        }
        int Y = matrix.length;
        int X = matrix[0].length;
        if ((col < X - 1 && matrix[0][col + 1] == target) || (row < Y - 1 && matrix[row + 1][0] == target)) {
            return true;
        }
        for (int i = 0; i < X; i++) {
            if (matrix[row][i] == target) {
                return true;
            }
        }
        for (int i = 0; i < Y; i++) {
            if (matrix[i][col] == target) {
                return true;
            }
        }
        return false;
    }

    public static int binarySearchByCol(int[][] matrix, int target) {
        int L = 0;
        int R = matrix[0].length - 1;
        int lessEquals = -1;
        while (L <= R) {
            int mid = (R + L) / 2;
            if (matrix[0][mid] >= target) {
                R = mid - 1;
            } else {
                lessEquals = mid;
                L = mid + 1;
            }
        }
        return lessEquals;
    }

    public static int binarySearchByRow(int[][] matrix, int target) {
        int L = 0;
        int R = matrix.length - 1;
        int row = -1;
        while (L <= R) {
            int mid = (R + L) / 2;
            if (matrix[mid][0] >= target) {
                R = mid - 1;
            } else {
                row = mid;
                L = mid + 1;
            }
        }
        return row;
    }

    public static boolean binarySearch(int[] matrix, int target, int left, int right) {
        while (left <= right) {
            int mid = (right + left) / 2 ;
            if (matrix[mid] > target) {
                right = mid - 1;
            } else if (matrix[mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4, 5}};
        int target = 2;
        System.out.println(searchMatrix1(matrix, target));
    }
}
