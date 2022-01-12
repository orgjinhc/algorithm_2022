package leetcode.daily.medium;

public class Daily_74_searchMatrix {

    /**
     * 核心技巧
     * 二分获取小于等最右边的位置 or 大于等于最左位置
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int[] subMatrix : matrix) {
            int min = subMatrix[0];
            int max = subMatrix[subMatrix.length - 1];

            if (target > max || target < min) {
                continue;
            }

            return binarySearch(subMatrix, target);
        }
        return false;
    }

    public static boolean binarySearch(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        while (L <= R) {
            int mid = (R + L) / 2;
            if (nums[mid] > target) {
                R = mid - 1;
            } else if (nums[mid] == target) {
                return true;
            } else {
                L = mid + 1;
            }
        }
        return false;
    }
}