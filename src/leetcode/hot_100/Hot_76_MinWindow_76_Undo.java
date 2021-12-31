package leetcode.hot_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s 、一个字符串 t
 * 返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 输入：
 * s = "ADOBECODEBANC",
 * t = "A  B C"
 */
public class Hot_76_MinWindow_76_Undo {

    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        if (s.equals(t)) {
            return s;
        }

        if (t.length() == 1) {
            int idx = s.indexOf(t);
            if (idx == -1) {
                return "";
            }
            return t;
        }

        int maxSize = 0;
        List<List<Integer>> levelList = new ArrayList<>();

        //  构建每个字符出现的位置信息
        for (int i = 0; i < t.length(); i++) {
            List<Integer> list = process(s, String.valueOf(t.charAt(i)));
            if (list.size() == 0) {
                return "";
            }
            maxSize = Math.max(list.size(), maxSize);
            levelList.add(list);
        }

        //  填充空位
        for (List<Integer> curList : levelList) {
            if (curList.size() == maxSize) {
                continue;
            }
            int remainingSize = maxSize - curList.size();
            while (--remainingSize >= 0) {
                curList.add(curList.get(curList.size() - 1));
            }
        }

        int[] boundary = getBoundary(levelList);
        int len = boundary[1] - boundary[0] + 1;
        int remaining = len - t.length();
        return remaining >= 0 ? s.substring(boundary[0], boundary[1] + 1) : s.substring(boundary[0], boundary[1] - remaining + 1);
    }

    public static List<Integer> process(String s, String index) {
        List<Integer> ans = new ArrayList<>();
        int idx = s.indexOf(index);
        while (idx != -1) {
            ans.add(idx);
            idx = s.indexOf(index, idx + 1);
        }
        return ans;
    }

    private static int[] getBoundary(List<List<Integer>> gridList) {
        int N = gridList.size();
        int startIndex = N - 2;
        List<List<Integer>> comboList = new ArrayList<>();
        for (int i = startIndex; i >= 0; i--) {
            if (i != startIndex) {
                comboList = comboSource(comboList, gridList.get(i));
            } else {
                comboList = combo(gridList.get(i), gridList.get(i + 1));
            }
        }
        int[] ans = new int[2];
        int lessMore = Integer.MAX_VALUE;
        for (List<Integer> subList : comboList) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (Integer integer : subList) {
                max = Math.max(max, integer);
                min = Math.min(min, integer);
            }
            if (lessMore > max - min) {
                lessMore = max - min;
                ans[0] = min;
                ans[1] = max;
            }
        }
        return ans;
    }

    public static List<List<Integer>> comboSource(List<List<Integer>> comboList, List<Integer> sourceList) {
        List<List<Integer>> ans = new ArrayList<>();
        for (Integer integer : sourceList) {

            for (List<Integer> subList : comboList) {
                List<Integer> curAns = new ArrayList<>();
                curAns.add(integer);
                for (Integer integer1 : subList) {
                    curAns.add(integer1);
                }
                ans.add(curAns);
            }
        }
        return ans;
    }

    public static List<List<Integer>> combo(List<Integer> sourceList1, List<Integer> sourceList2) {
        List<List<Integer>> ans = new ArrayList<>();
        for (Integer num1 : sourceList1) {
            for (Integer nums2 : sourceList2) {
                List<Integer> curAns = new ArrayList<>();
                curAns.add(num1);
                curAns.add(nums2);
                ans.add(curAns);
            }
        }
        return ans;
    }

    /**
     * "cabwefgewcwaefgcf"
     * "cae"
     * <p>
     * "aefgc"
     * "cabwefgewcw aefgc f"
     * 11 -> 15
     * <p>
     * "cwae"
     * "cabwefgew cwae fgcf"
     * 9 -> 12
     *
     * @param args
     */
    public static void main(String[] args) {
        String sourceStr = "acbbaca";
        String targetStr = "aba";
        System.out.println(minWindow(sourceStr, targetStr));
    }
}