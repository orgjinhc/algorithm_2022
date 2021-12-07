package leetcode.hot_100;

import java.util.Stack;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses_32 {

    /**
     * "()(()"
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        // dp[i] : 子串必须以i位置结尾的情况下，往左最远能扩出多长的有效区域
        int[] dp = new int[str.length];
        int pre = 0;
        int ans = 0;
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ')') {
                // 与str[i]配对的左括号的位置 pre
                // pre 谁和当前i位置的)匹配, 找到位置
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "(()";
        System.out.println(longestValidParentheses(s));
    }
}
