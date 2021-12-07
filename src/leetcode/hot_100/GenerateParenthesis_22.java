package leetcode.hot_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class GenerateParenthesis_22 {

    /**
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs("", n, n, ans);
        return ans;
    }


    public static void dfs(String path, int left, int right, List<String> ans) {
        if (left == 0 && right == 0) {
            ans.add(path);
        } else {

            if (left > right) {
                return;
            }

            if (left > 0) {
                dfs(path + "(", left - 1, right, ans);
            }
            if (right > 0) {
                dfs(path + ")", left, right - 1, ans);
            }
        }
    }

    public static void main(String[] args) {
        int n = 2;
        System.out.println(generateParenthesis(n));
    }
}
