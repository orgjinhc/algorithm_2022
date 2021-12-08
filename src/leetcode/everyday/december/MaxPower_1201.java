package leetcode.everyday.december;

import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class MaxPower_1201 {


    public static int maxPower2(String s) {
        if (null == s || s.length() < 1) {
            return 0;
        }
        int ans = 1;
        int L = 0;
        int R = 1;
        Stack<Character> ansStack = new Stack<>();
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


    public static int maxPower(String s) {
        if (null == s || s.length() < 1) {
            return 0;
        }

        if (s.length() < 2) {
            return 1;
        }
        int ans = 1;
        Stack<Character> ansStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (ansStack.isEmpty()) {
                ansStack.push(s.charAt(i));
            } else {
                if (ansStack.peek().equals(s.charAt(i))) {
                    ansStack.push(s.charAt(i));
                } else {
                    ans = Math.max(ans, ansStack.size());
                    while (!ansStack.isEmpty()) {
                        ansStack.pop();
                    }
                    ansStack.push(s.charAt(i));
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "cc";
        System.out.println(maxPower2(s));
    }
}
