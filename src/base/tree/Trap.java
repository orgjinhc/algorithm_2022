package base.tree;

public class Trap {

    public static int trap2(int[] height) {
        if (null == height || height.length < 3) {
            return 0;
        }
        int leftMax = height[0];
        int rightMax = height[height.length - 1];
        int L = 1;
        int R = height.length - 2;
        int ans = 0;
        while (L <= R) {
            if (leftMax <= rightMax) {
                ans += Math.max(0, leftMax - height[L]);
                leftMax = Math.max(leftMax, height[L++]);
            } else {
                ans += Math.max(0, rightMax - height[R]);
                rightMax = Math.max(rightMax, height[R--]);
            }
        }
        return ans;
    }

    public static int trap(int[] height) {
        int length = height.length;
        if (null == height || length < 3) {
            return 0;
        }

        int[] leftMax = new int[length];
        int[] rightMax = new int[length];
        //  预处理
        leftMax[0] = height[0];
        rightMax[length - 1] = height[length - 1];
        for (int i = 1; i < length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        for (int i = length - 1; i > 0; i--) {
            rightMax[i - 1] = Math.max(rightMax[i], height[i - 1]);
        }

        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            ans += Math.max(Math.min(leftMax[i - 1], rightMax[i + 1]) - height[i], 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(arr));
        System.out.println(trap2(arr));
    }
}
