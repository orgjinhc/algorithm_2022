package leetcode.daily;

/**
 * https://leetcode-cn.com/problems/target-sum/submissions/
 */
public class FindTargetSumWays_494 {

    public static int findTargetSumWays(int[] arr, int target) {
        return subs(arr, 0, 0, target);
    }

    /**
     * 子序列
     *
     * @param arr
     * @param index
     * @param curSum
     * @param target
     */
    public static int subs(int[] arr, int index, int curSum, int target) {
        //  没数了
        if (arr.length == index) {
            return target == curSum ? 1 : 0;
        }
        //  当前 +
        int selected = curSum + arr[index];
        //  当前 -
        int noSelected = curSum - arr[index];
        //  汇总结果
        return subs(arr, index + 1, selected, target) + subs(arr, index + 1, noSelected, target);
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1};
        System.out.println(findTargetSumWays(arr, 3));
    }
}
