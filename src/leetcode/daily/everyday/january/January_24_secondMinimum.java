package leetcode.daily.everyday.january;

import java.util.*;

public class January_24_secondMinimum {

    public static int secondMinimum(int n, int[][] edges, int time, int change) {
        // 处理无向图路径
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph[from].add(to);
            graph[to].add(from);
        }

        // dist1[i] 记录到节点i的最短路径
        // dist2[i] 记录到节点i的次最短路径
        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];
        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(dist2, Integer.MAX_VALUE);
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0});
        while (dist2[n] == Integer.MAX_VALUE) {
            int[] arr = queue.poll();
            int from = arr[0];
            int step = arr[1];
            //  以cur为起点所有边的点
            for (int to : graph[from]) {
                int shortStep = step + 1;
                // 更新最短的步数
                if (shortStep < dist1[to]) {
                    dist1[to] = shortStep;
                    queue.offer(new int[]{to, shortStep});
                } else if (shortStep > dist1[to] && shortStep < dist2[to]) {// 次最短路径步数 并加入队列中
                    dist2[to] = shortStep;
                    queue.offer(new int[]{to, shortStep});
                }
            }
        }

        //  计算次最短路径的时间
        int result = 0;
        for (int i = 0; i < dist2[n]; i++) {
            //  需要加入等红绿灯的时间 由于红绿转换 所以循环是时间 2*change
            //  遇到红灯
            if (result % (2 * change) >= change) {
                //  比如  time等于3 change=5 这个时候result如果等于6 就需要等4分钟 （2*5）-6%10 = 10-6 = 4
                result = result + (2 * change - result % (2 * change));
            }
            //  加上路径的时间
            result = result + time;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{1, 2}, {1, 3}, {1, 4}, {3, 4}, {4, 5}};
        int time = 3;
        int change = 5;
        secondMinimum(n, edges, time, change);
    }
}
