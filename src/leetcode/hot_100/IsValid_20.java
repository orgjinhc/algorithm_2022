package leetcode.hot_100;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 */
public class IsValid_20 {

    public static boolean isValid(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        if (s.charAt(0) == ')' || s.charAt(0) == ']' || s.charAt(0) == '}') {
            return false;
        }

        Stack<Character> ans = new Stack<>();
        ans.push(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                ans.push(s.charAt(i));
            } else {
                if (ans.isEmpty()) {
                    return false;
                }
                Character pop = ans.pop();
                if (s.charAt(i) == ')') {
                    if (pop != '(') {
                        return false;
                    }
                }

                if (s.charAt(i) == ']') {
                    if (pop != '[') {
                        return false;
                    }
                }

                if (s.charAt(i) == '}') {
                    if (pop != '{') {
                        return false;
                    }
                }
            }
        }

        return ans.isEmpty();

    }

    public static void main(String[] args) {

    }
}
