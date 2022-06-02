package leetcode.hot_100;

import leetcode.util.LCUtil;

import java.util.Arrays;


/**
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 输入：nums = [0]
 * 输出：[0]
 * 输入：nums = [1]
 * 输出：[1]
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * 链接：https://leetcode-cn.com/problems/sort-colors
 */
public class Hot_75_SortColors_75 {

    public static void sortColors(int[] nums) {
        int L = 0;
        int R = nums.length - 1;
        int mid = L + 1;
        while (mid <= R) {
            //  L 0 ... R match -> 0 移动到 L : 最小的数移动到左边
            if (L < mid && nums[mid] == 0) {
                LCUtil.swap(nums, L, mid);
                L++;
            } else if (R > mid && nums[mid] == 2) { // L 2 ... R match -> 2 移动到 R : 最大的数移动到右边
                LCUtil.swap(nums, R, mid);
                R--;
            } else {
                //  左边的数比中间的还大, 两个位置上的数字交换
                if (nums[mid] < nums[L]) {
                    LCUtil.swap(nums, mid, L);
                } else if (nums[mid] > nums[R]) { //  右边的数比中间的数还小, 交换
                    LCUtil.swap(nums, mid, R);
                } else {
                    //  数字相同无需排序, 移动游标, 找到需要排序的位置 -> 从 L+1 扫描到 R-1
                    mid++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        LCUtil.print(nums);
    }
}