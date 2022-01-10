package leetcode.daily.everyday.december;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * <p>
 * 选择某个下标 i并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * <p>
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations
 */
public class Daily_1203_LargestSumAfterKNegations {


    public static int largestSumAfterKNegations(int[] nums, int k) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        boolean zeroFlag = false;
        int absMinIndex = 0;
        //  小根堆, 元组内元素大小排序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> nums[a]));
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                priorityQueue.add(i);
            }
            if (nums[i] == 0) {
                zeroFlag = true;
            }
            if (Math.abs(nums[absMinIndex]) > Math.abs(nums[i])) {
                absMinIndex = i;
            }
        }
        //  负数的数量大于需要翻转的数量
        if (k <= priorityQueue.size()) {
            while (k-- > 0) nums[priorityQueue.peek()] = -nums[priorityQueue.poll()];
        } else {
            //  这是负数的数量小于需要翻转的数量, 先把所有负数全部翻转
            while (!priorityQueue.isEmpty() && k-- > 0) nums[priorityQueue.peek()] = -nums[priorityQueue.poll()];

            //  如果有0, 默认所有剩余的次数都给到0
            //  如果没有0, 判断k剩余次数是奇数还是偶数, 如果是偶数也不用管, 默认一个最小数翻转偶数次还是当前数
            //  如果没有0, k还剩余奇数次就需要将最小值在翻转一次
            //  无0并且k的剩余次数是奇数
            if (!zeroFlag && k % 2 != 0) nums[absMinIndex] = -nums[absMinIndex];
        }
        int ans = 0;
        for (int num : nums) {
            ans += num;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] num = {-1, -2, -3, -4, 1, 2, 3};
        System.out.println(largestSumAfterKNegations(num, 3));
    }
}
