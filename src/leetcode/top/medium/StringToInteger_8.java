package leetcode.top.medium;

public class StringToInteger_8 {

    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        //  去除空格
        s = s.trim();
        //  去除非数字位置
        s = removeHeadZero(s);
        if (s == null || s.equals("")) {
            return 0;
        }
        boolean negative = s.charAt(0) == '-';
        //  除数
        int minQ = Integer.MIN_VALUE / 10;
        //  余数
        int minR = Integer.MIN_VALUE % 10;
        //  默认进位的0
        int ans = 0;
        int cur;
        for (int i = (s.charAt(0) == '-' || s.charAt(0) == '+') ? 1 : 0; i < s.length(); i++) {

            //  由于负数的取值范围比正数大1, 先将所有整数全部转换为负数
            //  s.charAt(i) = 3, cur = -3
            cur = '0' - s.charAt(i);

            //  minQ = -214748364
            //  如果 ans < minQ, ans 取值范围[-214748365...]
            //  如果 ans = minQ, 但 cur < minR(-8), cur 取值范围[-9...]
            //  满足下面任何一个条件, 得到的最终结果必将越界(32位整数), 立即返回即可
            if ((ans < minQ) || (ans == minQ && cur < minR)) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            //  从头位置开始构造结果
            ans = ans * 10 + cur;
        }

        //  当前字符串不是负数, 但是得到的结果是 Integer.MIN_VALUE, 证明当前结果就是 Integer.MAX_VALUE
        if (!negative && ans == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        return negative ? ans : -ans;
    }

    public static String removeHeadZero(String str) {
        int N = str.length();
        //  符号标识位
        boolean symbolPosition = (str.startsWith("+") || str.startsWith("-"));
        //  s 记录第一个不是'0'字符的位置
        int s = symbolPosition ? 1 : 0;
        for (; s < N; s++) {
            if (str.charAt(s) != '0') {
                break;
            }
        }
        //  e 记录最左的 不是数字字符的位置
        int e = -1;
        // 左 <- 右
        // word 0099
        // 0099 1199
        for (int i = N - 1; i >= (symbolPosition ? 1 : 0); i--) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                e = i;
            }
        }
        return (symbolPosition ? String.valueOf(str.charAt(0)) : "") + str.substring(s, e == -1 ? N : e);
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("-123"));
    }
}
