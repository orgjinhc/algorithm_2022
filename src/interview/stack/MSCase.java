package interview.stack;

import java.util.ArrayList;
import java.util.List;

public class MSCase {

    /**
     * 前缀和功能
     *
     * @param arr
     * @return
     */
    public static int[] tireArr(int[] arr) {
        if (null == arr || arr.length < 1) {
            return null;
        }
        int index = 0;
        int[] ans = new int[arr.length];
        ans[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            ans[index + 1] = ans[index++] + arr[i];
        }
        return ans;
    }

    /**
     * 全排列数组
     *
     * @param arr
     * @return
     */
    public static List<int[]> fullPermutation(int[] arr) {
        if (null == arr || arr.length < 1) {
            return null;
        }
        List<int[]> ansList = new ArrayList<>();

        //  外层步长 L...L+1...L+2...n-1
        for (int L = 0; L < arr.length; L++) {

            //  内层步长 L...n-1 L+1...n-1 L+2...n-1  n -1...n-1
            for (int R = L; R < arr.length; R++) {
                int[] ans = new int[R - L + 1];
                int index = 0;

                //  内层每走一步构建一个新数组，左边界的外层L，右边界的内层R L...L  L...L+1  L...L+2  L...n-1
                for (int i = L; i <= R; i++) {
                    ans[index++] = arr[i];
                }
                ansList.add(ans);
            }
        }
        return ansList;
    }

    /**
     * 最小值
     *
     * @param arr
     * @return
     */
    public static int min(int[] arr) {
        if (null == arr || arr.length < 1) {
            return -1;
        }
        int min = Integer.MAX_VALUE;
        for (int L = 0; L < arr.length; L++) {
            min = Math.min(min, arr[L]);
        }
        return min;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        //  暴力解法 - 所有组合集合
        List<int[]> ansList = fullPermutation(arr);

        int globalMin = Integer.MAX_VALUE;

        for (int[] ints : ansList) {
            //  数组所有数的最大和
            int maxSum = tireArr(ints)[ints.length - 1];
            //  最小值
            int min = min(ints);

            System.out.print(maxSum * min + "  ");
            globalMin = Math.min(globalMin, maxSum * min);
        }
        System.out.println();
        System.out.println(globalMin);
    }
}
