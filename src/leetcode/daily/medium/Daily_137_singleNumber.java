package leetcode.daily.medium;

import leetcode.util.LCUtil;

import java.util.*;

/**
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 */
public class Daily_137_singleNumber {

    public static int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        //  时间\空间O(n)
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public static int singleNumberByQuickSort(int[] nums) {
        if (nums.length < 4) {
            return nums[0];
        }
        List<Integer> ans = new ArrayList<>();
        quickSort(nums, 0, nums.length - 1, ans);
        return nums[ans.get(0)];
    }

    public static void quickSort(int[] nums, int L, int R, List<Integer> ans) {
        if (L >= R) {
            return;
        }
        int i = L + new Random().nextInt(R - L + 1);
        LCUtil.swap(nums, i, R);
        int M = partition1(nums, L, R);
        quickSort(nums, L, M - 1, ans);
        quickSort(nums, M + 1, R, ans);
    }

    /**
     * 一个区间问题
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int partition1(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        //  将 L...R 变为左右有序的两部分, 任意返回其中一部分即可
        int lessEquals = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                LCUtil.swap(arr, index, ++lessEquals);
            }
            index++;
        }
        LCUtil.swap(arr, ++lessEquals, R);
        return lessEquals;
    }

    /**
     * 两个区间问题
     *
     * @param nums
     * @param L
     * @param R
     * @return
     */
    public static int partition(int[] nums, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEquals = L - 1;
        int mostEquals = R;
        while (L < mostEquals) {
            if (nums[R] > nums[L]) {
                LCUtil.swap(nums, ++lessEquals, L++);
            } else if (nums[R] == nums[L]) {
                L++;
            } else {
                LCUtil.swap(nums, --mostEquals, L);
            }
        }
        LCUtil.swap(nums, R, mostEquals);
        return lessEquals;
    }

    public static void main(String[] args) {
        int[] nums = {30000, 500, 100, 30000, 100, 30000, 100};
        System.out.println(singleNumberByQuickSort(nums));
    }
}