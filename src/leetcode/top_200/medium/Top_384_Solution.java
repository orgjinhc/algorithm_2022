package leetcode.top_200.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 * <p>
 * 实现 Solution class:
 * <p>
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * 输出
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 * <p>
 * 解释
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
 * solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
 * solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 200
 * -106 <= nums[i] <= 106
 * nums 中的所有元素都是 唯一的
 * 最多可以调用 5 * 104 次 reset 和 shuffle
 * <p>
 * 链接：https://leetcode-cn.com/problems/shuffle-an-array
 */
public class Top_384_Solution {

    private int[] init;
    private List<int[]> ans;
    private Random random = new Random();

    public Top_384_Solution(int[] nums) {
        this.init = nums;
//        ans = new ArrayList<>();
//        qbl(nums, 0, ans);
    }

    public int[] reset() {
        return init;
    }

    /**
     * Knuth 洗牌算法，在 O(n) 复杂度内等概率返回某个方案。
     * 对于下标为 0 位置，从 [0, n - 1] 随机一个位置进行交换，共有 n 种选择；
     * 下标为 1 的位置，从 [1, n - 1] 随机一个位置进行交换，共有 n−1 种选择 ...
     *
     * @return
     */
    public int[] shuffle() {
        int[] ans = init.clone();
        int N = ans.length;
        for (int i = 0; i < N; i++) {
            swap(ans, i, i + random.nextInt(N - i));
        }
        return ans;
    }

    /**
     * 全排列解决方案
     * n!的时间复杂度
     *
     * @param nums
     * @param index
     * @param ans
     */
    public static void qbl(int[] nums, int index, List<int[]> ans) {
        if (index == nums.length) {
            ans.add(Arrays.copyOf(nums, nums.length));
        } else {
            for (int i = index; i < nums.length; i++) {
                swap(nums, i, index);
                qbl(nums, index + 1, ans);
                swap(nums, i, index);
            }
        }
    }

    public static void swap(int[] nums, int L, int R) {
        int tmp = nums[R];
        nums[R] = nums[L];
        nums[L] = tmp;
    }

    public static void main(String[] args) {
        List<int[]> ans = new ArrayList<>();
        int[] nums = {1, 2, 3, 4};
        qbl(nums, 0, ans);

        for (int[] an : ans) {
            for (int i : an) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
