package base.recursive.classic.dp.从左到右模型;

public class 货币求最少张数问题 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 10};
        System.out.println(minZhang(arr, 10));
    }

    public static int minZhang(int[] arr, int aim) {
        return f(arr, 0, aim);
    }

    /**
     * arr[index...N-1] 上组合出rest = 0情况的最少货币张数
     *
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    public static int f(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            int curAns = f(arr, index + 1, rest - zhang * arr[index]);
            if (curAns != Integer.MAX_VALUE) {
                ans = Math.min(ans, curAns + zhang);
            }
        }
        return ans;
    }

}
