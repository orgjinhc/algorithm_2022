package interview.kmp;

public class KMP {

    public static int[] getNextArray(String match) {
        if (match.length() == 1) {
            return new int[]{-1};
        }
        char[] matchChars = match.toCharArray();

        int[] next = new int[matchChars.length];
        next[0] = -1;
        next[1] = 0;
        //  起始位置
        int i = 2;
        //  cn位置的字符是当前和L-1位置比较的字符
        int cn = 0;
        while (i < matchChars.length) {
            if (matchChars[i - 1] == matchChars[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static int process(String str, String match) {
        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();
        //  str数组索引位置
        int x = 0;
        //  match数组的索引位置
        int y = 0;
        int[] next = getNextArray(match);

        //  循环源数组Str, 从左向右遍历寻找与目标match数组匹配的位置
        while (x < strChars.length) {
            //  两个数组匹配到相同元素, x/y 同时右移
            if (strChars[x] == matchChars[y]) {
                x++;
                y++;
                //  match数组一直没有在源数组匹配到元素, 源数组一直向右移动
            } else if (y == 0) {
                x++;
                //  目标match数组已经在源str数组内匹配到了一些元素，当出现第一个不匹配的元素后
                //  目标match数组找到当前不匹配位置y的最大回文位置继续尝试和源str数组匹配
            } else {
                y = next[y];
            }
        }
        return y == matchChars.length ? x - y : -1;
    }

    public static void main(String[] args) {
        String str = "abdabccbc";
        String match = "cbc";
        System.out.println(process(str, match));
    }
}
