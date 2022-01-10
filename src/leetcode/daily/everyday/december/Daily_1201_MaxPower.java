package leetcode.daily.everyday.december;

public class Daily_1201_MaxPower {


    public static int maxPower2(String s) {
        if (null == s || s.length() < 1) {
            return 0;
        }
        int ans = 1;
        int L = 0;
        int R = 1;
        while (R < s.length()) {
            if (s.charAt(L) == s.charAt(R)) {
                R++;
            } else {
                ans = Math.max(ans, R - L);
                L = R++;
            }
        }
        return Math.max(ans, R - L);
    }

    public static void main(String[] args) {
        String s = "cc";
        System.out.println(maxPower2(s));
    }
}