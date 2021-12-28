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

    public static void main(String[] args) {
        String str = "ab";
        System.out.println(lengthOfLongestSubstring(str));
    }
}
