package base.recursive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Subsequence {

    public static void process(String str) {
        String path = "";
        List<String> ans = new ArrayList<>();
        subs(str, 0, ans, path);


        List<List<String>> ansList = new ArrayList<>();
        for (String an : ans) {
            ansList.add(Collections.singletonList(an));
        }

        for (List<String> arr : ansList) {
            System.out.println(arr.toString());
        }
    }

    /**
     * 子序列
     *
     * @param str   a b c d
     * @param index
     * @param ans
     * @param path
     */
    public static void subs(String str, int index, List<String> ans, String path) {
        if (str.length() == index) {
            ans.add(path);
            return;
        }

        String selected = path + str.charAt(index);
        subs(str, index + 1, ans, selected);

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


    public static void main(String[] args) {
        String ans = "abcd";
        process(ans);
    }
}
