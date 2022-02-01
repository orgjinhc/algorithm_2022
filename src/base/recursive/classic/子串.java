package base.recursive.classic;

/**
 * 子串:从左向右必须连续的子串
 * 0 - a ab abc abcd
 * 1 - b bc bcd
 * 2 - c cd
 * 3 - d
 */
public class 子串 {

    public static void process(String str) {
        if (null == str || str.length() < 1) {
            System.out.println("null");
            return;
        }

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            String curChar = "" + chars[i];
            System.out.print(curChar);
            for (int j = i + 1; j < chars.length; j++) {
                curChar = curChar + "" + chars[j];
                System.out.print(" " + curChar);
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        String ans = "abcd";
        process(ans);
    }
}
