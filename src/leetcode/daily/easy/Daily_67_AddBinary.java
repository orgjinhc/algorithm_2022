package leetcode.daily.easy;

/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字1和0。
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * 链接：https://leetcode-cn.com/problems/add-binary
 */
public class Daily_67_AddBinary {

    public static String addBinary1(String a, String b) {
        int N = a.length();
        int M = b.length();
        String ans = "";
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (N > 0 || M > 0) {
            int n = 0;
            if (N > 0) {
                n = Integer.valueOf(a.charAt(--N) - '0');
            }
            int m = 0;
            if (M > 0) {
                m = Integer.valueOf(b.charAt(--M) - '0');
            }
            int sum = n + m + carry;
            sb.append((sum) % 2);
            carry = (sum) / 2;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public String addBinary2(String a, String b) {
        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
    }

    public static String addBinary(String strA, String strB) {
        int a = Integer.valueOf(strA);
        int b = Integer.valueOf(strB);
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return String.valueOf(sum);
    }

    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";
        System.out.println(addBinary(a, b));
    }
}