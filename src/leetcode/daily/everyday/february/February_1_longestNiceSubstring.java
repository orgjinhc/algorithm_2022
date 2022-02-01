package leetcode.daily.everyday.february;

import java.util.HashSet;
import java.util.Set;

public class February_1_longestNiceSubstring {

    public static String longestNiceSubstring(String s) {
        int n = s.length();
        String ans = "";
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String substring = s.substring(i, j + 1);
                if (j - i + 1 > ans.length() && check(substring)) {
                    ans = substring;
                }
            }
        }
        return ans;
    }

    public static boolean check(String s) {
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            set.add(c);
        }

        for (char c : chars) {
            char a = Character.toLowerCase(c);
            char b = Character.toUpperCase(c);
            if (!set.contains(a) || !set.contains(b)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
