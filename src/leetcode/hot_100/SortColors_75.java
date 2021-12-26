package leetcode.hot_100;

import leetcode.util.LCUtil;

import java.util.Arrays;


/**
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * <p>
 * 进阶：
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/sort-colors
 */
public class SortColors_75 {

    public static void sortColorsBySort(int[] nums) {
        Arrays.sort(nums);
    }

    public static void sortColors(int[] nums) {
        int L = 0;
        int R = nums.length - 1;
        int F = 1;
        while (F <= R) {
            if (L < F && nums[F] == 0) {
                LCUtil.swap(nums, L, F);
                L++;
            } else if (R > F && nums[F] == 2) {
                LCUtil.swap(nums, R, F);
                R--;
            } else {
                if (nums[F] < nums[L]) {
                    LCUtil.swap(nums, F, L);
                } else if (nums[F] > nums[R]) {
                    LCUtil.swap(nums, F, R);
                } else {
                    F++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 0};
        sortColors(nums);
        print(nums);
    }

    public static void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
