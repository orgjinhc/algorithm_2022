package leetcode.hot_100;

/**
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 链接：https://leetcode-cn.com/problems/next-permutation
 */
public class NextPermutation_31 {

    public void nextPermutation(int[] nums) {
        if (null == nums || nums.length < 2) {
            return;
        }
        int endIndex = nums.length - 1;
        for (int R = endIndex; R >= 1; R--) {
            //  第一个升序位置 R和R-1
            if (nums[R] > nums[R - 1]) {
                //  从R到end位置找大于R-1的元素
                for (int i = endIndex; i >= R; i--) {
                    if (nums[i] > nums[R - 1]) {
                        //  找到元素交换i和R-1的数
                        swap(nums, i, R - 1);
                        //  R-1到end位置反转
                        reverse(nums, R, endIndex);
                    }
                }
            }
        }
    }

    /**
     * 交换元素
     *
     * @param nums
     * @param L
     * @param R
     */
    private static void swap(int[] nums, int L, int R) {
        int tmp = nums[L];
        nums[L] = nums[R];
        nums[R] = tmp;
    }

    /**
     * 反转数组
     *
     * @param nums
     * @param L
     * @param R
     */
    private static void reverse(int[] nums, int L, int R) {
        while (L <= R) {
            swap(nums, L++, R--);
        }
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 8, 6, 4, 5, 7};
        for (int num : nums) {
            System.out.print(" " + num);
        }
        System.out.println();
        reverse(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.print(" " + num);
        }
        System.out.println();
    }
}
