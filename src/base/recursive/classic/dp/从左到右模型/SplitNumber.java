package base.recursive.classic.dp.从左到右模型;

public class SplitNumber {

    public static void main(String[] args) {
        int[] arr = {2, 1, 7, 9, 4, 3};
        System.out.println(splitNumber(arr));
    }

    public static int splitNumber(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int sum = 0;
        for (int i : arr) {
            sum += i;
        }

        return f(arr, 0, sum >> 1);
    }

    /**
     * arr[index....] 返回不大于rest的最大的组合数
     *
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    private static int f(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return 0;
        }
        int noSelected = f(arr, index + 1, rest);
        int selected = 0;
        if (rest >= arr[index]) {
            selected = arr[index] + f(arr, index + 1, rest - arr[index]);
        }
        int ans = Math.max(noSelected, selected);
        System.out.println(ans);
        return ans;
    }
}
