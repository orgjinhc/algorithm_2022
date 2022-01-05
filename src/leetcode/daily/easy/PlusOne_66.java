package leetcode.daily.easy;

/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 链接：https://leetcode-cn.com/problems/plus-one
 */
public class PlusOne_66 {

    /**
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        if (null == digits || digits.length < 1) {
            return new int[0];
        }
        int length = digits.length;
        //  从后向前遍历, 保证最低位不是9
        for (int i = length; i > 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] ans = new int[length + 1];
        ans[0] = 1;
        return ans;
    }

    public static void main(String[] args) {

    }
}
