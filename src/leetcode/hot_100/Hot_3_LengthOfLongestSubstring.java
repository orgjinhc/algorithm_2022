package leetcode.hot_100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class Hot_3_LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int ans = Integer.MIN_VALUE;
        int L = 0;
        int R = 0;
        while (R < s.length()) {
            char charAt = s.charAt(R);
            //  包含情况, 重复位置出现
            if (map.containsKey(charAt)) {
                //  第一次出现位置
                int firstDuplicateKey = map.get(charAt);
                //  计算距离
                ans = Math.max(ans, R - L);
                //  窗口移动(重复位置下一位置)
                L = L <= firstDuplicateKey ? firstDuplicateKey + 1 : L;
            }
            map.put(charAt, R++);
        }
        //  case 情况
        return Math.max(ans, R - L);
    }

    public static int lengthOfLongestSubstringByZ(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        //  s小写字母组成, 默认长度26, 通过dp标记位置, dp含义:上一个位置的答案(不重复字符串的最远个数)
        //  dp默认每个位置都是-1
        int[] dp = new int[256];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = -1;
        }
        //  初始化字符0 位置
        dp[s.charAt(0)] = 0;
        int ans = 1;
        int pre = 1;
        for (int i = 1; i < s.length(); i++) {
            //  两个限定条件
            //  当前字符上一个位置, 没有出现就等于 i+1 距离, 出现就等于 i-dp[i]
            int p1 = i - dp[s.charAt(i)];
            //  上一个位置的最远距离, +1 后表示当前距离可达到的最远距离
            int p2 = pre + 1;

            //  二者取最近作为自己的答案
            int cur = Math.min(p1, p2);
            //  当前位置的答案和之前的答案求最大
            ans = Math.max(ans, cur);
            //  同时更新pre的大小, 下一个位置需要使用自己当前的答案
            pre = cur;
            //  再更新字符i的位置
            dp[s.charAt(i)] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "abcdefa";
        System.out.println(lengthOfLongestSubstringByZ(str));
    }
}
