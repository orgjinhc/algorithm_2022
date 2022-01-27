package base.recursive.classic;

import leetcode.util.LCUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 子序列:从左向右可以不连续的序列
 */
public class 子序列 {

    public static void main(String[] args) {
        String ans = "abcd";
        process(ans);
    }

    public static void process(String str) {
        String path = "";
        List<String> ans = new ArrayList<>();
        subs(str, 0, ans, path);
        LCUtil.printList(ans);
    }

    /**
     * 子序列函数
     *
     * @param str   固定参数
     * @param index 当前位置
     * @param ans   收集到子序列
     * @param path  存放之前决定
     */
    public static void subs(String str, int index, List<String> ans, String path) {
        if (str.length() == index) {
            ans.add(path);
            return;
        }

        //  要当前位置的字符
        String selected = path + str.charAt(index);
        subs(str, index + 1, ans, selected);
        //  不要当前位置的字符
        String noSelected = path;
        subs(str, index + 1, ans, noSelected);
    }

    /**
     * HashSet 用于去重
     *
     * @param str
     * @param index
     * @param ans
     * @param path
     */
    public static void notRepeatSubs(String str, int index, HashSet<String> ans, String path) {
        if (str.length() == index) {
            ans.add(path);
            return;
        }
        String selected = path + str.charAt(index);
        notRepeatSubs(str, index + 1, ans, selected);
        String noSelected = path;
        notRepeatSubs(str, index + 1, ans, noSelected);
    }
}