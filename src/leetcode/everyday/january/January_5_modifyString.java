package leetcode.everyday.january;

public class January_5_modifyString {

    public static String modifyString(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '?') {
                continue;
            }
            char last = ' ';
            char next = ' ';
            if (i > 0) {
                last = chars[i - 1];
            }
            if (i < chars.length - 1) {
                next = chars[i + 1];
            }
            for (int j = 0; j < 26; j++) {
                char cur = (char) ('a' + j);
                if (cur != last && cur != next) {
                    chars[i] = cur;
                    break;
                }
            }
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args) {
        String s = "??s";
        String ans = modifyString(s);
        System.out.println(ans);
    }
}