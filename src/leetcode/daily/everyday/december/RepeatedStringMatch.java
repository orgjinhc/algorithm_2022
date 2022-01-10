package leetcode.daily.everyday.december;

/**
 * 输入：a = "abcd", b = "cdabcdab"
 * 输出：3
 * 解释：a 重复叠加三遍后为 "ab cdabcdab cd", 此时 b 是其子串。
 * 示例 2：
 * <p>
 * 输入：a = "a", b = "aa"
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：a = "a", b = "a"
 * 输出：1
 * 示例 4：
 * <p>
 * 输入：a = "abc", b = "wxyz"
 * 输出：-1
 * <p>
 * 链接：https://leetcode-cn.com/problems/repeated-string-match
 */
public class RepeatedStringMatch {


    public static int repeatedStringMatch(String a, String b) {
        if (a.equals(b)) {
            return 1;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(a);
        int ans = 1;
        while (builder.length() < b.length()) {
            builder.append(a);
            ans++;
        }

        int idx = builder.indexOf(b);
        if (idx != -1) {
            return ans;
        }

        builder.append(a);
        ++ans;
        idx = builder.indexOf(b);
        return idx == -1 ? -1 : ans;
    }

    public static void main(String[] args) {
        System.out.println(repeatedStringMatch("a", "wxyz"));
    }
}
