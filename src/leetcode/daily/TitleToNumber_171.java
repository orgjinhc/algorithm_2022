package leetcode.daily;

/**
 * 给你一个字符串columnTitle ，表示 Excel 表格中的列名称。返回该列名称对应的列序号。
 * 例如，
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-number
 */
public class TitleToNumber_171 {

    public static int titleToNumber(String columnTitle) {
        int ans = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            //  columnTitle[i] 代表当前位置是上面字符
            int num = columnTitle.charAt(i) - 'A' + 1;
            //  单纯的 i 代表位数,  表示下一位从什么数开始
            ans = ans * 26 + num;
        }
        return ans;
    }

    public static void main(String[] args) {
        String columnTitle = "FXSHRXW";
        System.out.println(titleToNumber(columnTitle));
    }
}
