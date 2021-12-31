package leetcode.daily;

import java.util.List;

/**
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * https://leetcode-cn.com/problems/pascals-triangle-ii/
 */
public class GetRow_119 {

    public static List<Integer> getRow(int rowIndex) {
        return Generate_118.generate(rowIndex + 1).get(rowIndex);
    }

    public static void main(String[] args) {
        System.out.println(getRow(3));
    }
}
