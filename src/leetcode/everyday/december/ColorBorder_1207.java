package leetcode.everyday.december;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 * <p>
 * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
 * <p>
 * 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
 * <p>
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/coloring-a-border
 */
public class ColorBorder_1207 {


    /**
     * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
     * 输出：[[3,3],[3,2]]
     * <p>
     * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
     * 输出：[[1,3,3],[2,3,3]]
     * <p>
     * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
     * 输出：[[2,2,2],[2,1,2],[2,2,2]]
     *
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     */
    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;
        //  边集合
        List<int[]> borders = new ArrayList<>();

        //  核心点1：用于标记访问状态
        boolean[][] visited = new boolean[m][n];
        visited[row][col] = true;

        //  深度优先遍历获取到所有边框
        DFS(grid, row, col, visited, borders, grid[row][col]);
        //  对结果进行染色
        for (int i = 0; i < borders.size(); i++) {
            int x = borders.get(i)[0];
            int y = borders.get(i)[1];
            grid[x][y] = color;
        }
        return grid;
    }

    /**
     * 深度优先实现：找到一个非边框的点，立马进行处理
     *
     * @param grid          原网格矩阵
     * @param x             x坐标
     * @param y             y坐标
     * @param visited       标记访问状态数组
     * @param borders       边界集合
     * @param originalColor 原色
     */
    private static void DFS(int[][] grid, int x, int y, boolean[][] visited, List<int[]> borders, int originalColor) {
        int m = grid.length;
        int n = grid[0].length;
        boolean isBorder = false;

        //  核心点2：定义四个方向的移动策略
        int[][] direc = {
                {0, 1}, //  水平右移
                {0, -1}, //  水平左移
                {1, 0}, //  垂直上移
                {-1, 0} //  垂直下移
        };

        //  核心点3：四个方向尝试
        for (int i = 0; i < 4; i++) {
            int nx = direc[i][0] + x;
            int ny = direc[i][1] + y;

            //  核心点4：定义边框条件
            //  判断是否出界 nx >= 0 && nx < m && ny >= 0 && ny < n
            //  判断是否同一联通区域 grid[nx][ny] == originalColor
            if (!(nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == originalColor)) {
                isBorder = true;
            } else if (!visited[nx][ny]) {  //  核心点5：判断访问状态并设置访问状态
                visited[nx][ny] = true;
                DFS(grid, nx, ny, visited, borders, originalColor);
            }
        }
        if (isBorder) {
            borders.add(new int[]{x, y});
        }
    }

    /**
     * 广度优先遍历，先把上下左右四个方向的非边框的节点收集上来，慢慢处理
     *
     * @param grid
     * @param row
     * @param col
     * @param color
     * @return
     */
    public static int[][] BFS(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> borders = new ArrayList<>();
        int originalColor = grid[row][col];
        int[][] direc = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row, col});
        visited[row][col] = true;
        while (!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0];
            int y = xy[1];

            boolean isBorder = false;
            for (int i = 0; i < 4; i++) {
                int nx = direc[i][0] + x;
                int ny = direc[i][1] + y;

                //  nx >= 0 && nx < m && ny >= 0 && ny < n 在网格矩阵内
                //  grid[nx][ny] == originalColor 原色相同
                if (!(nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == originalColor)) {
                    isBorder = true;
                } else if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
            if (isBorder) {
                borders.add(new int[]{x, y});
            }
        }

        for (int[] border : borders) {
            grid[border[0]][border[1]] = color;
        }
        return grid;
    }

    public static void main(String[] args) {
        //  DFS:找到一个没有访问的边节点, 马上对当前节点进行判断
        //  BFS:找到一个没有访问的边节点, 不会马上对这个节点进行判断, 先加到队列内, 完成当前节点的判断找到所有的非边节点, 再完成后续节点的判断
        int[][] grid = {
                {1, 1, 2, 3, 2, 2},
                {2, 2, 3, 2, 3, 3},
                {1, 2, 3, 2, 2, 2},
                {1, 2, 3, 2, 2, 2},
        };
        int[][] ints = colorBorder(grid, 0, 2, 4);

        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
