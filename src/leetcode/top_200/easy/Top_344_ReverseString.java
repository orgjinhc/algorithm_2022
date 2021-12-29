package leetcode.top_200.easy;

import leetcode.util.LCUtil;

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 示例 1：
 * <p>
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 * <p>
 * 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * <p>
 * 提示：
 * 1 <= s.length <= 105
 * s[i] 都是 ASCII 码表中的可打印字符
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-string
 */
public class Top_344_ReverseString {

    public static void reverseString(char[] s) {
        int L = 0;
        int R = s.length - 1;
        while (L <= R) {
            char tmp = s[R];
            s[R] = s[L];
            s[L] = tmp;
            L++;
            R--;
        }
    }

    public static void main(String[] args) {
        String str = "Hannah";
        char[] ans = str.toCharArray();
        reverseString(ans);

        for (char an : ans) {
            System.out.print(an + " ");
        }
        System.out.println();
    }
}