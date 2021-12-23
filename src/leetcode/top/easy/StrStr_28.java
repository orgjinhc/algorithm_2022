package leetcode.top.easy;

public class StrStr_28 {

    public static int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        if (haystack.equals("")) {
            return -1;
        }
        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        System.out.println(strStr("hello", " "));
    }
}
