package interview.sliding_window;

import java.util.LinkedList;

/**
 * 给你一个数组arr, 可以任意的构成子数组SubArr, 要求子数组内最大值减去最小值小于一个给定的num
 * 求满足上面这个条件的子数组数量
 */
public class MostCount {

    /**
     * 暴力方式构建所有子数组
     *
     * @param arr
     * @param num
     * @return
     */
    public static int processBao(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int count = 0;
        // 当前层的目的是定义数组起始位置
        for (int L = 0; L < arr.length; L++) {
            //  L...L+1...L+2......arr.length - 1
            //  当前层是定义数组最大长度
            for (int R = L; R < arr.length; R++) {
                //  L-R...L-R+1...L-R+2...L-arr.length - 1
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;

                //  当前L - R范围组成的数组, 是否满足要求
                for (int i = L; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }

                if (max - min <= num) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 利用单调栈特性实现
     *
     * @param arr
     * @param num
     * @return
     */
    public static int process(int[] arr, int num) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        LinkedList<Integer> qMax = new LinkedList<>();
        LinkedList<Integer> qMin = new LinkedList<>();

        int ans = 0;
        int L = 0;
        int R = 0;

        //  二层循环必然逃不掉, 外层定义起始位置, 内存定义满足条件的数组长度
        while (L < arr.length) {
            //  内层确认当前不满足的右边界。当L追上R之前, R左侧的所有范围都满足当前条件
            while (R < arr.length) {
                //  滑动窗口 - 大->小
                while (!qMax.isEmpty() && arr[qMax.peekLast()] < arr[R]) {
                    qMax.pollLast();
                }
                qMax.addLast(R);

                //  滑动窗口 - 小->大
                while (!qMin.isEmpty() && arr[qMin.peekLast()] > arr[R]) {
                    qMin.pollLast();
                }
                qMin.addLast(R);

                //  上面两步已经维护了两个滑动窗口, 只需要对比两个窗口内的值是否满足条件
                if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num) {
                    break;
                }
                R++;
            }
            //  当前起始位置到数组最大长度满足条件的数组数量
            ans += R - L;

            //  两个队列需要随着L的位置动态调整
            if (qMax.peekFirst() == L) {
                qMax.pollFirst();
            }
            if (qMin.peekFirst() == L) {
                qMin.pollFirst();
            }
            L++;
        }
        return ans;
    }

    public static void main(String[] args) {

        int[] ans = {2, 1, 7};
        System.out.println(processBao(ans, 4));
        System.out.println(process(ans, 4));
    }
}
