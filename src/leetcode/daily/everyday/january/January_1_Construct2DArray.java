package leetcode.daily.everyday.january;

/**
 * 给你一个下标从 0开始的一维整数数组original和两个整数m和n。你需要使用original中所有元素创建一个m行n列的二维数组。
 * original中下标从 0到 n - 1（都 包含 ）的元素构成二维数组的第一行，下标从 n到 2 * n - 1（都 包含）的元素构成二维数组的第二行，依此类推。
 * 请你根据上述过程返回一个m x n的二维数组。如果无法构成这样的二维数组，请你返回一个空的二维数组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：original = [1,2,3,4], m = 2, n = 2
 * 输出：[[1,2],[3,4]]
 * 解释：
 * 构造出的二维数组应该包含 2 行 2 列。
 * original 中第一个 n=2 的部分为 [1,2] ，构成二维数组的第一行。
 * original 中第二个 n=2 的部分为 [3,4] ，构成二维数组的第二行。
 * 示例 2：
 * <p>
 * 输入：original = [1,2,3], m = 1, n = 3
 * 输出：[[1,2,3]]
 * 解释：
 * 构造出的二维数组应该包含 1 行 3 列。
 * 将 original 中所有三个元素放入第一行中，构成要求的二维数组。
 * 示例 3：
 * <p>
 * 输入：original = [1,2], m = 1, n = 1
 * 输出：[]
 * 解释：
 * original 中有 2 个元素。
 * 无法将 2 个元素放入到一个 1x1 的二维数组中，所以返回一个空的二维数组。
 * 示例 4：
 * <p>
 * 输入：original = [3], m = 1, n = 2
 * 输出：[]
 * 解释：
 * original 中只有 1 个元素。
 * 无法将 1 个元素放满一个 1x2 的二维数组，所以返回一个空的二维数组。
 * 提示：
 * <p>
 * 1 <= original.length <= 5 * 104
 * 1 <= original[i] <= 105
 * 1 <= m, n <= 4 * 104
 * <p>
 * 链接：https://leetcode-cn.com/problems/convert-1d-array-into-2d-array
 */
public class January_1_Construct2DArray {

    public static int[][] construct2DArray(int[] original, int m, int n) {
        if (m * n != original.length) {
            return new int[0][];
        }
        int index = 0;
        int[][] ans = new int[m][n];
        for (int M = 0; M < m; M++) {
            for (int N = 0; N < n; N++) {
                ans[M][N] = original[index++];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {3};
        int[][] ints = construct2DArray(nums, 1, 2);
        for (int[] subList : ints) {
            for (Integer num : subList) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

}
