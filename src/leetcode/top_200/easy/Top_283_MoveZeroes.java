package leetcode.top_200.easy;

import leetcode.util.LCUtil;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 */
public class Top_283_MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes2(nums);
        LCUtil.print(nums);
    }

    public static void moveZeroes2(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num != 0) {
                nums[index++] = num;
            }
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void moveZeroes1(int[] nums) {
        int N = nums.length;
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] == 0) {
                int L = i + 1;
                int R = N - 1;
                while (L <= R && nums[L] != 0) {
                    int tmp = nums[L];
                    nums[L] = nums[L - 1];
                    nums[L - 1] = tmp;
                    L++;
                }
            }
        }
    }
}