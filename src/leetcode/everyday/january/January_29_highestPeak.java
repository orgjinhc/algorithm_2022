package leetcode.everyday.january;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class January_29_highestPeak {

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] ans = new int[m][n];
        //  预处理数组, 所有位置全部填上-1, 表示没有访问过
        for (int i = 0; i < m; ++i) {
            Arrays.fill(ans[i], -1);
        }

        //  根据题意将水域全部设置为高度为0, 并且将水域入队, 目的是将水域周围的陆地全部高度+1
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (isWater[i][j] == 1) {
                    ans[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            //  所有水域和已经算过高度的陆地区域
            int[] visited = queue.poll();
            int x = visited[0];
            int y = visited[1];
            //  BFS流程, 尝试四个方向是否存在没有访问过的陆地区域, 设置其高度为当前区域高度+1, 并将没有访问过的区域添加到队列内
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (0 <= nx && nx < m && 0 <= ny && ny < n && ans[nx][ny] == -1) {
                    ans[nx][ny] = ans[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return ans;
    }
}