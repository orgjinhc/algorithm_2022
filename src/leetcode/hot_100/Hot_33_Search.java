package leetcode.hot_100;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
public class Hot_33_Search {

    /**
     * 笔试阶段采用的方式, 暴力方式, 也是最简单方式
     *
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 面试阶段采用的方式, 2分
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search2(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int L = 0;
        int R = nums.length - 1;
        int M;
        while (L <= R) {
            M = (R + L) / 2;
            //  成功找到
            if (nums[M] == target) {
                return M;
            }
            //  L == M == R无法二分
            if (nums[L] == nums[M] && nums[M] == nums[R]) {
                while (L != M && nums[L] == nums[R]) {
                    L++;
                }
                if (L == M) {
                    L = M + 1;
                    continue;
                }
            }
            // arr[M] != num
            // [L] [M] [R] 不都一样的情况, 如何二分的逻辑
            if (nums[L] != nums[M]) {
                //  L...M存在断点
                if (nums[L] > nums[M]) {
                    //  确定target在M...R区间
                    if (target >= nums[M] && target <= nums[R]) {
                        L = M + 1;
                    } else {
                        //  确定target在L...M区间
                        R = M - 1;
                    }
                } else {//  L..M 有序。M..R存在断点
                    if (target >= nums[L] && target <= nums[M]) {
                        R = M - 1;
                    } else {
                        L = M + 1;
                    }
                }
            } else {
                //  刨除L == M == R的情况, 现在只可能是 L == M != R
                if (nums[M] < nums[R]) {
                    if (target >= nums[M] && target <= nums[R]) {
                        L = M + 1;
                    } else {
                        R = M - 1;
                    }
                } else {
                    if (target >= nums[L] && target < nums[M]) {
                        R = M - 1;
                    } else {
                        L = M + 1;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(search2(nums, 0));
    }
}
