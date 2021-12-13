package leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * https://leetcode-cn.com/problems/pascals-triangle/
 */
public class Generate_118 {

    /**
     * 输入: numRows = 5
     * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> curLevelAns = new ArrayList<>();
            curLevelAns.add(1);
            for (int j = 1; j < i - 1; j++) {
                curLevelAns.add(ans.get(i - 2).get(j - 1) + ans.get(i - 2).get(j));
            }
            if (curLevelAns.size() < i) {
                curLevelAns.add(1);
            }
            ans.add(curLevelAns);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> ans = generate(5);
        for (List<Integer> an : ans) {
            for (Integer integer : an) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
