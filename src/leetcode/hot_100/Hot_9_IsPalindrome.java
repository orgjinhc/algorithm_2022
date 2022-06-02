package leetcode.hot_100;

/**
 * 给你一个整数 x, 如果 x 是一个回文整数, 返回 true
 * 否则, 返回 false
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 */
public class Hot_9_IsPalindrome {

    /**
     * 利用reverse实现
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        int reverseNum = 0;
        int num = x;
        while (num != 0) {
            reverseNum = reverseNum * 10 + num % 10;
            num /= 10;
        }
        return reverseNum == x;
    }

    public static boolean isPalindrome(int x) {
        String target = processSourceStr(x);
        int ans = 0;
        for (int i = 1; i < target.length(); i++) {
            //  双指针
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < target.length()) {
                if (target.charAt(L) != target.charAt(R)) {
                    break;
                }
                ans = Math.max(ans, R++ - L-- + 1);
            }
        }
        return ans == target.length();
    }

    public static String processSourceStr(int x) {
        String s = String.valueOf(x);
        StringBuffer sb = new StringBuffer();
        String shift_3 = "#";
        for (int i = 0; i < s.length(); i++) {
            sb.append(shift_3).append(s.charAt(i));
        }
        return sb.append(shift_3).toString();
    }

    public static void main(String[] args) {
        int x = 1221;
        System.out.println(isPalindrome(x));
        System.out.println(isPalindrome2(x));
    }
}
