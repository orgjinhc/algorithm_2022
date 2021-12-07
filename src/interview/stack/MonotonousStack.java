package interview.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MonotonousStack {

    static class Info {
        int index;
        int leftMin;
        int rightMin;

        public Info(int index, int leftMin, int rightMin) {
            this.index = index;
            this.leftMin = leftMin;
            this.rightMin = rightMin;
        }
    }

    /**
     * 找到一个数左边最小的值和右边最小的值
     *
     * @param arr
     */
    public static List<Info> process(int[] arr) {
        if (boundaryProcessing(arr)) {
            return null;
        }
        List<Info> ans = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int rightMin = -1;
            int leftMin = -1;
            for (int L = i; L >= 0; L--) {
                if (arr[L] < arr[i]) {
                    leftMin = arr[L];
                    break;
                }
            }
            for (int R = i; R < arr.length; R++) {
                if (arr[R] < arr[i]) {
                    rightMin = arr[R];
                    break;
                }
            }
            ans.add(new Info(arr[i], leftMin, rightMin));
        }
        return ans;
    }

    /**
     * 找到一个数左边最小的值和右边最小的值
     *
     * @param arr
     */
    public static int[][] process1(int[] arr) {
        if (boundaryProcessing(arr)) {
            return null;
        }

        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int rightMin = -1;
            int leftMin = -1;
            for (int L = i; L >= 0; L--) {
                if (arr[L] < arr[i]) {
                    leftMin = arr[L];
                    break;
                }
            }
            for (int R = i; R < arr.length; R++) {
                if (arr[R] < arr[i]) {
                    rightMin = arr[R];
                    break;
                }
            }
            ans[i][0] = leftMin;
            ans[i][1] = rightMin;
        }
        return ans;
    }


    /**
     * 找到一个数左边最小的值和右边最小的值
     *
     * @param arr
     */
    public static int[][] process2(int[] arr) {
        if (boundaryProcessing(arr)) {
            return null;
        }

        int[][] ans = new int[arr.length][2];
        //  底->顶 小->大
        Stack<Integer> minToMax = new Stack<>();
        for (int i = 0; i < arr.length; i++) {

            //  碰到一个不满足栈定义的元素执行流程如下
            while (!minToMax.isEmpty() && arr[minToMax.peek()] > arr[i]) {
                //  弹出栈顶元素, 当前栈顶元素的右边界定义完毕, 继续寻找左边界, 逻辑:栈空的情况, 左边界-1(没有), 栈非空的情况, 左边界就是栈顶元素
                Integer topElement = minToMax.pop();
                int leftMinIndex = minToMax.isEmpty() ? -1 : minToMax.peek();
                ans[topElement][0] = leftMinIndex;
                ans[topElement][1] = i;
            }

            //  当前位置元素添加到栈内
            minToMax.push(i);
        }

        //  栈内还有元素, 继续执行寻找操作
        while (!minToMax.isEmpty()) {
            //  弹出栈顶元素, 当前栈顶元素的右边界定义完毕, 继续寻找左边界, 逻辑:栈空的情况, 左边界-1(没有), 栈非空的情况, 左边界就是栈顶元素
            Integer topElement = minToMax.pop();
            int leftMinIndex = minToMax.isEmpty() ? -1 : minToMax.peek();
            ans[topElement][0] = leftMinIndex;
            ans[topElement][1] = -1;
        }
        return ans;
    }

    /**
     * 找到一个数左边最小的值和右边最小的值
     *
     * @param arr
     */
    public static int[][] process3(int[] arr) {
        if (boundaryProcessing(arr)) {
            return null;
        }

        int[][] ans = new int[arr.length][2];
        //  底->顶 小->大
        Stack<List<Integer>> minToMax = new Stack<>();
        for (int i = 0; i < arr.length; i++) {

            //  碰到一个不满足栈定义的元素执行流程如下
            while (!minToMax.isEmpty() && arr[minToMax.peek().get(0)] > arr[i]) {
                //  弹出栈顶元素, 当前栈顶元素的右边界定义完毕, 继续寻找左边界, 逻辑:栈空的情况, 左边界-1(没有), 栈非空的情况, 左边界就是栈顶元素
                List<Integer> pops = minToMax.pop();
                for (Integer index : pops) {
                    int leftMinIndex = minToMax.isEmpty() ? -1 : minToMax.peek().get(minToMax.peek().size() - 1);
                    ans[index][0] = leftMinIndex;
                    ans[index][1] = i;
                }
            }

            //  当前位置元素添加到栈内
            if (!minToMax.isEmpty() && arr[minToMax.peek().get(0)] == arr[i]) {
                List<Integer> peek = minToMax.peek();
                peek.add(i);
            } else {
                List<Integer> indexes = new ArrayList<>();
                indexes.add(i);
                minToMax.push(indexes);
            }
        }

        //  栈内还有元素, 继续执行寻找操作
        while (!minToMax.isEmpty()) {
            //  弹出栈顶元素, 当前栈顶元素的右边界定义完毕, 继续寻找左边界, 逻辑:栈空的情况, 左边界-1(没有), 栈非空的情况, 左边界就是栈顶元素
            List<Integer> pops = minToMax.pop();
            for (Integer index : pops) {
                int leftMinIndex = minToMax.isEmpty() ? -1 : minToMax.peek().get(minToMax.peek().size() - 1);
                ans[index][0] = leftMinIndex;
                ans[index][1] = -1;
            }
        }
        return ans;
    }

    private static boolean boundaryProcessing(int[] arr) {
        if (null == arr || arr.length < 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arg = {2, 3, 1, 7, 5, 6, 4};
        arg = new int[]{2, 2, 3, 1};
        int[][] ans = process3(arg);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i][0] + " " + ans[i][1]);
            System.out.println();
        }
    }
}
