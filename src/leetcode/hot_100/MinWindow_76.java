package leetcode.hot_100;

import leetcode.SortUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串 s 、一个字符串 t
 * 返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 输入：
 * s = "ADOBECODEBANC",
 * t = "A  B C"
 */
public class MinWindow_76 {

    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        if (s.equals(t)) {
            return s;
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

        int[] ans = new int[2];
        //  求最小覆盖字串
        int colSize = levelList.get(0).size();
        int rowSize = levelList.size();
        int lessMore = Integer.MIN_VALUE;
        for (int col = 0; col < colSize; col++) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int row = 0; row < rowSize; row++) {
                Integer index = levelList.get(row).get(col);
                max = Math.max(max, index);
                min = Math.min(min, index);
            }
            if (lessMore < max - min) {
                lessMore = max - min;
                ans[0] = min;
                ans[1] = max;
            }
        }
        return s.substring(ans[0], ans[1] + 1);
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
//        System.out.println(minWindow("cabwefgewcwaefgcf", "cae"));
        int[][] grid = {
                {0, 9, 15},
                {1, 11, 11},
                {4, 7, 12}
        };
        int M = grid.length;
        int N = grid[0].length;
    }

}
