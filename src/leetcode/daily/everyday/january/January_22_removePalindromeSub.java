package leetcode.daily.everyday.january;

import java.util.*;

public class January_22_removePalindromeSub {

    public static int removePalindromeSub(String s) {
        if (s == null || s.length() < 1) {
            return 1;
        }
        int L = 0;
        int R = s.length() - 1;
        while (L < R) {
            if (s.charAt(L) != s.charAt(R)) {
                return 2;
            }
            L++;
            R--;
        }
        return 1;
    }

    public static void main(String[] args) {
        String s = "baabb";
        System.out.println(removePalindromeSub(s));
    }
}
