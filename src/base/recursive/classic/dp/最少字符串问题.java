package base.recursive.classic.dp;

/**
 * 范围模型
 */
public class 最少字符串问题 {

    public static int process(int[] arr) {
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    /**
     * 先手选牌
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int f(int[] arr, int L, int R) {
        //  边界条件:剩一张牌时
        if (L == R) {
            return arr[L];
        }

        return Math.max(
                arr[L] + s(arr, L + 1, R),
                arr[R] + s(arr, L, R - 1));
    }

    /**
     * 后手选牌
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int s(int[] arr, int L, int R) {
        //  剩一张牌没牌可选
        if (L == R) {
            return 0;
        }
        return Math.min(
                f(arr, L + 1, R),
                f(arr, L, R - 1)
        );

    }

    public static void main(String[] args) {
        int[] arr = {4, 7, 9, 5};
        System.out.println(process(arr));
    }
}
