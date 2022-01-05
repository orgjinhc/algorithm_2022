package leetcode.daily.easy;

/**
 * 在二维数组grid中，grid[i][j]代表位于某处的建筑物的高度。 我们被允许增加任何数量（不同建筑物的数量可能不同）的建筑物的高度。 高度 0 也被认为是建筑物。
 * <p>
 * 最后，从新数组的所有四个方向（即顶部，底部，左侧和右侧）观看的“天际线”必须与原始数组的天际线相同。 城市的天际线是从远处观看时，由所有建筑物形成的矩形的外部轮廓。 请看下面的例子。
 * <p>
 * 建筑物高度可以增加的最大总和是多少？
 * 链接：https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline
 */
public class MaxIncreaseKeepingSkylin_807 {


    /**
     * [
     * [3, 0, 8, 4],
     * [2, 4, 5, 7],
     * [9, 2, 6, 3],
     * [0, 3, 1, 0]
     * ]
     *
     * @param grid
     * @return
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {

        if (null == grid) {
            return 0;
        }
        int R = grid.length;
        int C = grid[0].length;
        int[] rowMax = new int[R];
        int[] colMax = new int[C];
        //  R[]
        for (int i = 0; i < R; i++) {
            int RMax = Integer.MIN_VALUE;
            for (int j = 0; j < C; j++) {
                RMax = Math.max(RMax, grid[i][j]);
            }
            rowMax[i] = RMax;
        }
        //  C[]
        for (int j = 0; j < C; j++) {
            int CMax = Integer.MIN_VALUE;
            for (int i = 0; i < R; i++) {
                CMax = Math.max(CMax, grid[i][j]);
            }
            colMax[j] = CMax;
        }
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
