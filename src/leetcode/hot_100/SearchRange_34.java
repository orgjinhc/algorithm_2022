package leetcode.hot_100;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 */
public class SearchRange_34 {

    /**
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {

        if (nums == null || nums.length < 1) {
            return new int[]{-1, -1};
        }
        int L = lessMostRight(nums, target);
        //  L到达左边界 or L下一位置不是target
        if (L == nums.length - 1 || nums[L + 1] != target) {
            return new int[]{-1, -1};
        }

        //  target存在数组内, target所在位置不是右边界,target左边界+1就是target的左区间.只需要找到target+1最左位置的下标就能定位target的右区间
        return new int[]{L + 1, lessMostRight(nums, target + 1)};
    }

    /**
     * 能2分的情况：有序
     * case1:找到小于目标数的最右位置
     * case2:找到大于目标数的最左位置
     * 不能2分的情况：无序, 特殊场景下也可以2分, 查看hot_32
     */
    public static void binarySearch(int[] nums, int target) {

    }

    /**
     * 更小的最右位置
     *
     * @param nums
     * @param target
     */
    public static int lessMostRight(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        int ans = -1;
        while (L <= R) {
            int M = (R + L) / 2;
            //  找到一个小于target的位置就标记
            if (nums[M] < target) {
                ans = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
        return ans;
    }

    /**
     * 更大的最左位置
     *
     * @param nums
     * @param target
     */
    public static int greaterMostLeft(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        int ans = -1;
        while (L <= R) {
            int M = (R + L) / 2;
            if (nums[M] <= target) {
                L = M + 1;
            } else {
                //  找到一个大于target的位置就标记
                ans = M;
                R = M - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(lessMostRight(nums, 8));
        System.out.println(greaterMostLeft(nums, 8));
    }
}
