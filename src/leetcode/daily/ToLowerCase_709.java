package leetcode.daily;

/**
 * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 * https://leetcode-cn.com/problems/to-lower-case/
 */
public class ToLowerCase_709 {

    public static String toLowerCase(String s) {
        if (s == null || s.length() < 1) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int charAt = s.charAt(i);
            if (charAt > 63 && charAt < 91) {
                builder.append((char) (charAt + 32));
            } else {
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = "Hello";
        System.out.println(toLowerCase(s));
    }
}
