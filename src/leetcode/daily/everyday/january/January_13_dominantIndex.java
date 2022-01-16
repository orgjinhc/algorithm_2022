package leetcode.daily.everyday.january;

public class January_13_dominantIndex {

    /**
     * 根据题意选择合适的下标标识最大和次大即可
     *
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        int N = nums.length;
        if (N == 1) {
            return 0;
        }
        int largest = 0;
        int secondLargest = -1;
        for (int index = 1; index < N; index++) {
            if (nums[index] > nums[largest]) {
                secondLargest = largest;
                largest = index;
            } else if (secondLargest == -1 || nums[index] > nums[secondLargest]) {
                secondLargest = index;
            }
        }
        return nums[largest] >= (2 * nums[secondLargest]) ? largest : -1;
    }
}
