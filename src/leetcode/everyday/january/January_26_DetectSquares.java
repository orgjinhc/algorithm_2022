package leetcode.everyday.january;

public class January_26_DetectSquares {

    static int[][] container = new int[1011][1011];

    public January_26_DetectSquares() {

    }

    public void add(int[] point) {
        container[point[1]][point[0]]++;
    }

    public int count(int[] point) {
        int ans = leftDown(point);
        if (ans > 0) {
            return ans;
        }
        ans = leftUp(point);
        if (ans > 0) {
            return ans;
        }
        ans = rightUp(point);
        if (ans > 0) {
            return ans;
        }
        ans = rightDown(point);
        return ans;
    }

    private int rightDown(int[] point) {
        int xPoint = point[0];
        int yPoint = point[1];

        int ans = 0;
        int index = 1;
        while (yPoint + index <= 1000 && xPoint >= index) {
            //  y轴上存在平行线 x轴上存在平行线 point 对角线上存在点
            if (container[yPoint + index][xPoint] > 0 && container[yPoint][xPoint - index] > 0 && container[yPoint + index][xPoint - index] > 0) {
                ans = (ans == 0 ? 1 : ans) * container[yPoint + index][xPoint] * container[yPoint][xPoint - index] * container[yPoint + index][xPoint - index];
            }
            index++;
        }
        return ans;
    }

    private int rightUp(int[] point) {
        int xPoint = point[0];
        int yPoint = point[1];

        int ans = 0;
        int index = 1;
        while (yPoint + index <= 1000 && xPoint + index <= 1000) {
            //  y轴上存在平行线 x轴上存在平行线 point 对角线上存在点
            if (container[yPoint + index][xPoint] > 0 && container[yPoint][xPoint + index] > 0 && container[yPoint + index][xPoint + index] > 0) {
                ans = (ans == 0 ? 1 : ans) * container[yPoint + index][xPoint] * container[yPoint][xPoint + index] * container[yPoint + index][xPoint + index];
            }
            index++;
        }
        return ans;
    }

    public static int leftDown(int[] point) {
        int xPoint = point[0];
        int yPoint = point[1];

        int ans = 0;
        int index = 1;
        while (yPoint >= index && xPoint >= index) {
            //  y轴上存在平行线 x轴上存在平行线 point 对角线上存在点
            if (container[yPoint - index][xPoint] > 0 && container[yPoint][xPoint - index] > 0 && container[yPoint - index][xPoint - index] > 0) {
                ans = (ans == 0 ? 1 : ans) * container[yPoint - index][xPoint] * container[yPoint][xPoint - index] * container[yPoint - index][xPoint - index];
            }
            index++;
        }
        return ans;
    }

    public static int leftUp(int[] point) {
        int xPoint = point[0];
        int yPoint = point[1];

        int ans = 0;
        int index = 1;
        while (yPoint >= index && xPoint + index <= 1000) {
            //  y轴上存在平行线 x轴上存在平行线 point 对角线上存在点
            if (container[yPoint - index][xPoint] > 0 && container[yPoint][xPoint + index] > 0 && container[yPoint - index][xPoint + index] > 0) {
                ans = (ans == 0 ? 1 : ans) * container[yPoint - index][xPoint] * container[yPoint][xPoint + index] * container[yPoint - index][xPoint + index];
            }
            index++;
        }
        return ans;
    }

    public static void main(String[] args) {
        January_26_DetectSquares detectSquares = new January_26_DetectSquares();
        detectSquares.add(new int[]{419, 351});
        detectSquares.add(new int[]{798, 351});
        detectSquares.add(new int[]{798, 730});
        System.out.println(detectSquares.count(new int[]{419, 730}));
        detectSquares.add(new int[]{998, 1});
        detectSquares.add(new int[]{0, 999});
        detectSquares.add(new int[]{998, 999});
        System.out.println(detectSquares.count(new int[]{0, 1}));
        detectSquares.add(new int[]{226, 561});
        detectSquares.add(new int[]{269, 561});
        detectSquares.add(new int[]{226, 604});
        System.out.println(detectSquares.count(new int[]{269, 604}));
        detectSquares.add(new int[]{200, 274});
        detectSquares.add(new int[]{200, 793});
        detectSquares.add(new int[]{719, 793});
        System.out.println(detectSquares.count(new int[]{719, 274}));
    }
}
