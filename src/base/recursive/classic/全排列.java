package base.recursive.classic;

import leetcode.util.LCUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列:长度不变, 顺序变更的所有可能
 */
public class 全排列 {

    public static void main(String[] args) {
        LCUtil.printList(fullSequence1("acc"));
        LCUtil.printList(fullSequence2("acc"));
        LCUtil.printList(fullSequence3("acc"));
    }

    /**
     * 优秀 的 递归实现
     *
     * @param msg
     * @return
     */
    public static List<String> fullSequence1(String msg) {
        List<String> ans = new ArrayList<>();
        process(msg.toCharArray(), 0, ans);
        return ans;
    }

    public static void process(char[] chars, int index, List<String> ans) {
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
            return;
        }
        for (int j = index; j < chars.length; j++) {
            //  按位交换
            swap(chars, index, j);
            //  DFS逻辑
            process(chars, index + 1, ans);
            //  还原现场
            swap(chars, index, j);
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    /**
     * 易理解 的 递归实现
     *
     * @param msg
     * @return
     */
    public static List<String> fullSequence2(String msg) {
        List<String> ans = new ArrayList<>();
        char[] chars = msg.toCharArray();
        List<Character> rest = new ArrayList<>();
        for (char aChar : chars) {
            rest.add(aChar);
        }
        process2(rest, "", ans);
        return ans;
    }

    public static void process2(List<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
            return;
        }
        for (int i = 0; i < rest.size(); i++) {
            Character character = rest.get(i);
            rest.remove(i);
            process2(rest, path + character, ans);
            rest.add(i, character);
        }
    }


    /**
     * 去重 的 递归实现
     *
     * @param msg
     * @return
     */
    public static List<String> fullSequence3(String msg) {
        List<String> ans = new ArrayList<>();
        process3(msg.toCharArray(), 0, ans);
        return ans;
    }

    public static void process3(char[] chars, int index, List<String> ans) {
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
            return;
        }
        //  此处不可用全局变量
        boolean[] visited = new boolean[256];
        for (int j = index; j < chars.length; j++) {
            if (!visited[chars[j]]) {
                //  记录当前位置已经出现过
                visited[chars[j]] = true;
                //  按位交换
                swap(chars, index, j);
                //  DFS逻辑
                process3(chars, index + 1, ans);
                //  还原现场
                swap(chars, index, j);
            }
        }
    }
}