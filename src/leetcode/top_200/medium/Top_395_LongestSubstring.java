package leetcode.top_200.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串，要求该子串中的每一个字符出现次数都不少于 k 。返回这一子串的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 * <p>
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅由小写英文字母组成
 * 1 <= k <= 105
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
 */
public class Top_395_LongestSubstring {

    public static int longestSubstring1(String s, int k) {
        return dfs(s, k);
    }

    private static int dfs(String s, int k) {
        //  统计每个字符出现的次数
        int[] dp = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dp[s.charAt(i) - 'a']++;
        }

        //  统计不满足 <k 的字符
        String dissatisfyStr = "";
        for (int i = 0; i < dp.length; i++) {
            int count = dp[i];
            if (count >= k || count == 0) {
                continue;
            }
            dissatisfyStr = String.valueOf((char) (i + 'a'));
            break;
        }

        //  没有找到不满足的部分, 当前s的长度就是答案
        if (dissatisfyStr.equals("")) {
            return s.length();
        }

        //  得到不满足区间
        String[] dissatisfyArr = s.split(dissatisfyStr);
        int ans = 0;
        for (String s1 : dissatisfyArr) {
            ans = Math.max(ans, dfs(s1, k));
        }
        return ans;
    }

    public int longestSubstring(String s, int k) {
        int[] dp = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            dp[index]++;
        }

        List<Integer> ans = new ArrayList<>();
        for (int an : dp) {
            if (k > an) {
                continue;
            }
            ans.add(an);
        }

        int L = 0;
        int R = s.length();
        for (Integer charAt : ans) {
            int idx = s.indexOf(charAt);
            while (idx != -1) {

            }
        }
        return 1;
    }

    public static void main(String[] args) {
        String s = "aacbbb";
        System.out.println(longestSubstring1(s, 2));
    }
}