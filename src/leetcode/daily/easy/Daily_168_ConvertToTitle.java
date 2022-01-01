package leetcode.daily.easy;

/**
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * 示例 1：
 * <p>
 * 输入：columnNumber = 1
 * 输出："A"
 * 示例 2：
 * <p>
 * 输入：columnNumber = 28
 * 输出："AB"
 * 示例 3：
 * <p>
 * 输入：columnNumber = 701
 * 输出："ZY"
 * 示例 4：
 * <p>
 * 输入：columnNumber = 2147483647
 * 输出："FXSHRXW"
 * 提示：
 * <p>
 * 1 <= columnNumber <= 231 - 1
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/excel-sheet-column-title
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Daily_168_ConvertToTitle {

    /**
     * 对于一般性的进制转换题目，只需要不断地对 columnNumber 进行 % 运算取得最后一位
     * 本题目明显是26以后开始进位, 索引 columnNumber 不断的模 26
     *
     * @param columnNumber
     * @return
     */
    public static String convertToTitle(int columnNumber) {
        String ans = "";
        if (columnNumber == 0) {
            return ans;
        }
        while (columnNumber > 0) {
            //  每次进位后--, 保证从0开始
            columnNumber--;
            //  'A' + columnNumber % 26 ～= 1，2，3，4，5
            ans = (char) ('A' + columnNumber % 26) + ans;
            //  进位
            columnNumber /= 26;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(2147483647));
    }
}
