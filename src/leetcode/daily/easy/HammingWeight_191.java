package leetcode.daily.easy;

/**
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011中，共有三位为 '1'。
 * <p>
 * 链接：https://leetcode-cn.com/problems/number-of-1-bits
 */
public class HammingWeight_191 {

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int ans = 0;
        int rightOne = 0;
        while (n != 0) {
            ans++;
            //  n =                 01010101010101010101010101010101
            //  ~n =                10101010101010101010101010101010
            //  ~n + 1 =            10101010101010101010101010101011
            //  ~n + 1 = -n =       10101010101010101010101010101011
            //  n & (-n) =          00000000000000000000000000000001
            //  n ^ (n & (-n)) =    01010101010101010101010101010100
            rightOne = n & (-n);
            n ^= rightOne;
        }
//        while (n != 0) {
//            if ((n & 1) == 1) {
//                ans += 1;
//            }
//            n >>= 1;
//        }
        return ans;
    }

    public static void main(String[] args) {
    }
}
