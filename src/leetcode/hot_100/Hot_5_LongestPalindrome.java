package leetcode.hot_100;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class Hot_5_LongestPalindrome {


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
        int left = 0;
        int right = 0;
        int max = 1;
        for (int i = 1; i < target.length(); i++) {
            //  双指针
            int L = i - 1;
            int R = i + 1;
            //  控制指针扩充行为, 每一位都可以向两个边界扩充
            while (L >= 0 && R < target.length()) {
                if (target.charAt(L) != target.charAt(R)) {
                    break;
                }
                //  当前得到的最大距离大于目前最大距离max, 更新
                if ((R - L + 1) > max) {
                    left = L / 2;
                    right = R / 2;
                    max = R - L + 1;
                }
                L--;
                R++;
            }
        }
        return s.substring(left, right);
    }

    /**
     * 处理源串
     *
     * @param s
     * @return
     */
    public static String processSourceStr(String s) {
        StringBuffer sb = new StringBuffer();
        String shift_3 = "#";
        for (int i = 0; i < s.length(); i++) {
            sb.append(shift_3).append(s.charAt(i));
        }
        return sb.append(shift_3).toString();
    }

    public static void main(String[] args) {
        String s = "baab";
        System.out.println(longestPalindrome(s));
    }
}
