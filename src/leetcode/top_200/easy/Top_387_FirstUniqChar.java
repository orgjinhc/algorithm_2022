package leetcode.top_200.easy;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 示例：
 * <p>
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 * <p>
 * 提示：你可以假定该字符串只包含小写字母。
 * <p>
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 */
public class Top_387_FirstUniqChar {

    public static int firstUniqCharByIndexOf(String s) {
        for (int i = 0; i < s.length(); i++) {
            int idx = s.indexOf(s.charAt(i));
            if (idx != -1) {
                idx = s.substring(idx + 1).indexOf(s.charAt(i));
                if (idx == -1) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int firstUniqCharByDP(String s) {
        int[] dp = new int[26];
        for (int i = 0; i < s.length(); i++) {
            dp[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (dp[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int firstUniqCharByBaoli(String s) {
        boolean[] dp = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            boolean exist = false;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[s.charAt(i) - 'a'] = true;
                    exist = true;
                    break;
                }
            }
            if (!dp[s.charAt(i) - 'a'] && !exist) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(firstUniqCharByBaoli("aabb"));
    }
}
