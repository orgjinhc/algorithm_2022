package base.recursive;

import java.util.ArrayList;
import java.util.List;

public class Sequence {

    public static void process(char[] chars, int i, List<String> ans) {
        if (i == chars.length) {
            ans.add(String.valueOf(chars));
            return;
        }
        for (int j = i; j < chars.length; j++) {
            //  按位交换
            swap(chars, i, j);
            //  DFS逻辑
            process(chars, i + 1, ans);
            //  还原现场
            swap(chars, i, j);
        }

    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static void main(String[] args) {
        List<String> ans = new ArrayList<>();
        String msg = "abc";
        process(msg.toCharArray(), 0, ans);

        for (String an : ans) {
            System.out.print(" " + an);
        }
    }
}
