package leetcode.hot_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 */
public class Hot_17_LetterCombinations {


    public static List<String> letterCombinations(String digits) {

        char[][] phone = {
                {'a', 'b', 'c'}
        };

        List<String> ans = new ArrayList<>();
        process(digits.toCharArray(), 0, "", ans, phone);
        return ans;
    }

    public static void process(char[] nums, int index, String path, List<String> ans, char[][] phone) {
        if (index == nums.length) {
            ans.add(path);
        } else {
            char[] charAt = phone[nums[index] - '2'];
            for (char c : charAt) {
                path = path + c;
                process(nums, index + 1, path, ans, phone);
            }
        }
    }

    public static void main(String[] args) {
        String digits = "2";
        System.out.println(letterCombinations(digits));
    }
}
