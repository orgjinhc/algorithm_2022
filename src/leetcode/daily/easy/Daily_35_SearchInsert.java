package leetcode.daily.easy;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 * 示例2:
 * <p>
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 * 示例 4:
 * <p>
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 * 示例 5:
 * <p>
 * 输入: nums = [1], target = 0
 * 输出: 0
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 为无重复元素的升序排列数组
 * -104 <= target <= 104
 * <p>
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 */
public class Daily_35_SearchInsert {

    public static int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target) + 1;
    }

    /**
     * 找到小于等于target的最右边index
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        int L = 0;
        int R = nums.length - 1;
        int lessEquals = -1;
        while (L <= R) {
            int mid = (R + L) / 2;
            if (nums[mid] >= target) {
                R = mid - 1;
            } else {
                lessEquals = mid;
                L = mid + 1;
            }
        }
        return lessEquals;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
        System.out.println(searchInsert(nums, 3));
    }
}
