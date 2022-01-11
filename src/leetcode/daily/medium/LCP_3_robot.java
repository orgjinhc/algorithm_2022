package leetcode.daily.medium;

import java.util.HashSet;

/**
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 * <p>
 * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：command = "URR", obstacles = [], x = 3, y = 2
 * 输出：true
 * 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
 */
public class LCP_3_robot {

    public static boolean robot2(String command, int[][] obstacles, int x, int y) {
        int pathX = 0;
        int pathY = 0;
        for (char ch : command.toCharArray()) {
            if (ch == 'U') {
                pathY++;
            } else {
                pathX++;
            }
        }
        //  无论是否存在障碍, 以当前移动规则是否可以达到终点
        if (!canReach(pathX, pathY, command, x, y)) {
            return false;
        }

        //  到达终点的路径中是否路过障碍
        for (int[] obstacle : obstacles) {
            int crashX = obstacle[0];
            int crashY = obstacle[1];
            //  无法达到障碍坐标, 直接跳过当前障碍
            if (crashX > x || crashY > y) {
                continue;
            }
            //  当前路径规划是否能达到障碍点
            if (canReach(pathX, pathY, command, crashX, crashY)) {
                return false;
            }
        }
        return true;
    }

    private static boolean canReach(int pathX, int pathY, String command, int destX, int destY) {
        //  当前路径规划规则下, 想要到达目的地坐标最少移动多少轮
        int loopCnt = Math.min(destX / pathX, destY / pathY);
        //  到达目的地坐标还差的距离
        destX -= loopCnt * pathX;
        destY -= loopCnt * pathY;

        //  再在当前路径规划下, 继续走一轮看是否能crash
        for (char ch : command.toCharArray()) {
            if (destX == 0 && destY == 0) {
                return true;
            }
            if (ch == 'U') {
                destY--;
            } else {
                destX--;
            }
        }

        return destX == 0 && destY == 0;
    }

    public static boolean robot1(String command, int[][] obstacles, int x, int y) {
        int X = 0, Y = 0;
        HashSet<Integer> set = new HashSet<>();
        set.add(0);
        for (char c : command.toCharArray()) {
            X += c == 'R' ? 1 : 0;
            Y += c == 'U' ? 1 : 0;
            set.add((X << 10) | Y);
        }

        // 最终到达不了终点
        if (!meet1(X, Y, x, y, set)) {
            return false;
        }
        for (int[] ob : obstacles) {
            if (ob[0] <= x && ob[1] <= y && meet1(X, Y, ob[0], ob[1], set)) {
                return false;
            }
        }
        return true;
    }

    // 一轮以内，
    // X,往右一共有几个单位
    // Y,往上一共有几个单位
    // set, 一轮以内的所有可能性
    // (x,y)要去的点
    // 机器人从(0,0)位置，能不能走到(x,y)
    public static boolean meet1(int X, int Y, int x, int y, HashSet<Integer> set) {
        //  机器人只往右走
        if (X == 0) {
            return x == 0;
        }
        //  机器人只往上走
        if (Y == 0) {
            return y == 0;
        }
        // 至少几轮
        int atLeast = Math.min(x / X, y / Y);
        // 经历过最少轮数后，x剩多少？
        int rx = x - X * atLeast;
        // 经历过最少轮数后，y剩多少？
        int ry = y - Y * atLeast;
        return set.contains((rx << 10) | ry);
    }

    public static boolean robot(String command, int[][] obstacles, int x, int y) {
        String[] commands = command.split("");
        int[] path = new int[]{0, 0};
        boolean[][] dp = buildDp(obstacles);
        int X = dp.length;
        int Y = dp[0].length;
        while (true) {
            for (String c : commands) {
                moving(path, c);
                int moveX = path[0];
                int moveY = path[1];
                if (moveX > x || moveY > y || (X > moveX && Y > moveY && dp[moveX][moveY])) {
                    return false;
                }
                if (moveX == x && moveY == y) {
                    return true;
                }
            }
        }
    }

    public static void moving(int[] path, String command) {
        if ("U".equals(command)) {
            path[1]++;
        } else {
            path[0]++;
        }
    }

    public static boolean crash(int[] path, int[][] obstacles, boolean[][] dp) {
        for (int[] obstacle : obstacles) {
            int x = obstacle[0];
            int y = obstacle[1];
            if (path[0] > x && path[1] > y) {
                dp[x][y] = false;
            }
            if (x == path[0] && y == path[1]) {
                return true;
            }
        }
        return false;
    }

    public static boolean[][] buildDp(int[][] obstacles) {
        int xMax = 0;
        int yMax = 0;
        for (int[] obstacle : obstacles) {
            int x = obstacle[0];
            int y = obstacle[1];
            xMax = Math.max(xMax, x);
            yMax = Math.max(yMax, y);
        }
        boolean[][] dp = new boolean[xMax + 1][yMax + 1];
        for (int[] obstacle : obstacles) {
            int x = obstacle[0];
            int y = obstacle[1];
            dp[x][y] = true;
        }
        return dp;
    }

    public static void main(String[] args) {
        String command = "UUR";
        int[][] obstacles = new int[][]{{2, 2}};
        int x = 2, y = 4;
//        System.out.println(robot(command, obstacles, x, y));
//        System.out.println(robot1(command, obstacles, x, y));
        System.out.println(robot2(command, obstacles, x, y));
    }
}
