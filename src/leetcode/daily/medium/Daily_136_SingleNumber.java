package leetcode.daily.medium;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/single-number
 */
public class Daily_136_SingleNumber {

    /**
     * 异或实现, 异或满足交互律和结合律
     * <p>
     * a ^ a = 0;
     * a ^ 0 = a
     * <p>
     * tmp = a ^ b;
     * b = tmp ^ b;
     * a = tmp ^ a;
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 1};
        System.out.println(singleNumber(nums));
    }
}
