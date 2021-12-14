package leetcode.daily;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 */
public class IsPalindrome_125 {

    /**
     * 核心流程
     * 1.处理源串, 只保留大小写字母和数字三种类型的字符。大小转化成小写，其余不变
     * 2.默认找到回文中心, 奇数只有一个位置、偶数有两个位置.找到中心后通过两个指针依次向两边推
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

    /**
     * 处理源字符串
     *
     * @param s
     * @return
     */
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


    /**
     * 核心流程
     * 1.通过双指针从头和尾开始向中心移动, 沿途过滤掉非大小写字母和数字的字符即可
     * 2.只要有任何一个位置不同即可得出结论
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return true;
        }
        int L = 0;
        int R = s.length() - 1;
        while (L < R) {
            char leftChar = s.charAt(L);
            char rightChar = s.charAt(R);
            if (isValid(leftChar) && isValid(rightChar)) {
                if (!equals(leftChar, rightChar)) {
                    return false;
                }
                L++;
                R--;
            } else {
                L += isValid(leftChar) ? 0 : 1;
                R -= isValid(rightChar) ? 0 : 1;
            }
        }
        return true;
    }

    public static boolean isValid(char charAt) {
        if ((charAt >= 'a' && charAt <= 'z') || (charAt >= '0' && charAt <= '9') || (charAt >= 'A' && charAt <= 'Z')) {
            return true;
        }
        return false;
    }

    public static boolean equals(char leftChar, char rightChar) {
        return (leftChar == rightChar) || (Math.min(leftChar, rightChar) == Math.max(leftChar, rightChar) - 32);
    }


    public static void main(String[] args) {
        String s = "abcBA";
        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome2(s));
    }
}