package leetcode.daily;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 */
public class IsPalindrome_125 {

    /**
     * 示例 1:
     * <p>
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 解释："amanaplanacanalpanama" 是回文串
     * 示例 2:
     * <p>
     * 输入: "race a car"
     * 输出: false
     * 解释："raceacar" 不是回文串
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }
        String targetStr = process(s);
        if (targetStr.length() < 2) {
            return true;
        }
        int M = (targetStr.length() - 1 + 0) / 2;
        int L = M - 1;
        int R;
        if (targetStr.length() % 2 == 0) {
            if (targetStr.charAt(M) != targetStr.charAt(M + 1)) {
                return false;
            }
            R = M + 2;
        } else {
            R = M + 1;
        }
        while (L >= 0 && R <= targetStr.length() - 1) {
            if (targetStr.charAt(L--) != targetStr.charAt(R++)) {
                return false;
            }
        }
        return true;
    }

    public static String process(String s) {
        StringBuilder builder = new StringBuilder();
        //  预处理当前字符串
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if ((charAt >= 'a' && charAt <= 'z') || (charAt >= '0' && charAt <= '9')) {
                builder.append(charAt);
            }
            if (charAt >= 'A' && charAt <= 'Z') {
                builder.append((char) (charAt + 32));
            }
        }
        return builder.toString();
    }


    public static boolean isPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }
        String targetStr = process(s);
        if (targetStr.length() < 2) {
            return true;
        }


        return true;
    }


    public static void main(String[] args) {
        String s = "0P";
        System.out.println(isPalindrome(s));
    }
}