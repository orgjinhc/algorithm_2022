package leetcode.hot_100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class LongestPalindrome_05 {


    /**
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * <p>
     * 输入：s = "cbbc"
     * 输出："cbbc"
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (null == s || s.length() < 1) {
            return null;
        }
        //  奇数个字符和偶数个字符存在差异性, 统一处理一下
        String target = processSourceStr(s);
        int max = 1;
        String ans = "";
        for (int i = 1; i < target.length(); i++) {
            LinkedList<Character> queue = new LinkedList<>();
            //  双指针
            int L = i - 1;
            int R = i + 1;
            //  控制指针扩充行为, 每一位都可以向两个边界扩充
            while (L >= 0 && R < target.length()) {
                if (target.charAt(L) != target.charAt(R)) {
                    break;
                }
                if ((L & 1) != 0) {
                    queue.addFirst(target.charAt(L));
                    queue.addLast(target.charAt(R));
                }
                if (R - L + 1 > max) {
                    ans = queue.toString();
                    max = R - L + 1;
                }
                L--;
                R++;
            }
        }
        return ans.substring(1, ans.length() - 1).replace(",", "");
    }

    public static String processSourceStr(String s) {
        StringBuffer sb = new StringBuffer();
        String shift_3 = "#";
        for (int i = 0; i < s.length(); i++) {
            sb.append(shift_3).append(s.charAt(i));
        }
        return sb.append(shift_3).toString();
    }

    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(longestPalindrome(s));
    }
}
