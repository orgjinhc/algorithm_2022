package leetcode.daily;

public class Palindrome {

    public static boolean process1(String str) {
        if (null == str || str.length() < 1) {
            return false;
        }
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (count == -1) {
                return false;
            }
            if (chars[i] == '(') {
                count++;
            } else {
                count--;
            }
        }
        return count == 0;
    }

    public static int maxWeight(String str) {
        if (null == str || str.length() < 1) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int count = 0;
        int need = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                count++;
            } else {
                if (count == 0) {
                    need++;
                } else {
                    count--;
                }
            }
        }
        return count + need;
    }


    public static int maxMatchParent(String str) {
        if (null == str || str.length() < 1) {
            return 0;
        }

        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length];
        int ans = Integer.MIN_VALUE;
        int pre = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                //  记录i-1位置的左边界-1
                pre = i - dp[i - 1] - 1;
                //  pre 没有越界 并且 pre位置是 (, 有可能和当前i位置组成一个新的match长度
                if (pre >= 0 && chars[pre] == '(') {
                    //  边界问题处理
                    //  pre = 0 目的是为了保证i位置的数量得到正确计算
                    //  pre > 0 目的是添加pre-1位置的match数量
                    dp[i] = 2 + dp[i - 1] + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = ")))))";
        System.out.println(process1(str));
        System.out.println(maxWeight(str));
    }
}
