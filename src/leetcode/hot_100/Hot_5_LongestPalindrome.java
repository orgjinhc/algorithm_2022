package leetcode.hot_100;

/**
 * 给你一个字符串 s，找到 s 中最长回文子串。
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
        int ansLeft = 0;
        int ansRight = 0;

        int palindromicMax = 1;
        for (int midStr = 1; midStr < target.length(); midStr++) {
            //  双指针 检测当前位置构成的回文串长度
            int L = midStr - 1;
            int R = midStr + 1;
            //  越界case
            while (L >= 0 && R < target.length()) {
                if (target.charAt(L) != target.charAt(R)) {
                    break;
                }
                //  当前得到的最大距离大于目前最大距离max
                if ((R - L + 1) > palindromicMax) {
                    //  更新 max
                    ansLeft = L / 2;
                    ansRight = R / 2;
                    palindromicMax = R - L + 1;
                }
                L--;
                R++;
            }
        }
        return s.substring(ansLeft, ansRight);
    }

    /**
     * 处理源串
     *
     * @param s
     * @return
     */
    public static String processSourceStr(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            sb.append("#").append(s.charAt(i));
        }
        return sb.append("#").toString();
    }

    public static void main(String[] args) {
        String s = "baab";
        System.out.println(longestPalindrome(s));
    }
}
