package leetcode.everyday.january;

import java.util.*;

/**
 * 输入：blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * 输出：false
 * 解释：
 * 从源方格无法到达目标方格，因为我们无法在网格中移动。
 * 无法向北或者向东移动是因为方格禁止通行。
 * 无法向南或者向西移动是因为不能走出网格。
 * <p>
 * 链接：https://leetcode-cn.com/problems/escape-a-large-maze
 */
public class January_11_isPossible {

    static final int BLOCKED = -1;
    static final int VALID = 0;
    static final int FOUND = 1;

    public static boolean isEscapePossible1(int[][] blocked, int[] source, int[] target) {
        //  只有一个点, 无法形成包围圈, source 一定能到达 target
        Set<Pair> hashBlocked = new HashSet<>();
        for (int[] pos : blocked) {
            hashBlocked.add(new Pair(pos[0], pos[1]));
        }
        int result = check(blocked, source, target, hashBlocked);
        if (result == FOUND) {
            return true;
        } else if (result == BLOCKED) {
            return false;
        } else {
            result = check(blocked, target, source, hashBlocked);
            return result != BLOCKED;
        }
    }

    public static int check(int[][] blocked, int[] start, int[] finish, Set<Pair> hashBlocked) {
        int sx = start[0];
        int sy = start[1];
        int fx = finish[0];
        int fy = finish[1];
        int N = blocked.length;
        int countdown = N * (N - 1) / 2;

        int BOUNDARY = 1000000;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        Pair startPos = new Pair(sx, sy);

        Queue<Pair> queue = new ArrayDeque<>();
        queue.offer(startPos);

        Set<Pair> visited = new HashSet<>();
        visited.add(startPos);

        while (!queue.isEmpty() && countdown > 0) {
            Pair pair = queue.poll();
            //  开始位置(起点 or 下一个起点)
            int x = pair.x;
            int y = pair.y;

            /**
             * 不断的尝试上下左右四个方向
             */
            for (int d = 0; d < dirs.length; ++d) {
                //  下一个位置坐标
                int nx = x + dirs[d][0];
                int ny = y + dirs[d][1];
                Pair newPair = new Pair(nx, ny);

                /**
                 * 找到下一个正确位置后, 添加到队列. BFS流程
                 * !hashBlocked.contains(newPair) 不在障碍点
                 * !visited.contains(newPair) 没有访问过
                 */
                if (nx >= 0 && nx < BOUNDARY && ny >= 0 && ny < BOUNDARY && !hashBlocked.contains(newPair) && !visited.contains(newPair)) {
                    if (nx == fx && ny == fy) {
                        return FOUND;
                    }
                    --countdown;
                    /**
                     * 添加到队列
                     * 添加到访问列表
                     */
                    queue.offer(newPair);
                    visited.add(newPair);
                }
            }
        }
        if (countdown > 0) {
            return BLOCKED;
        }
        return VALID;
    }

    public static void main(String[] args) {
        int[][] blocked = {{0, 1}, {1, 0}};
        int[] source = {0, 0};
        int[] target = {2, 2};
        System.out.println(isEscapePossible1(blocked, source, target));
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}