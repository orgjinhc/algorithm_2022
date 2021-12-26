package leetcode.top.easy;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串""。
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 提示：
 * <p>
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 */
public class Top_14_LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String curStr = strs[i];
            int index = 0;
            while (index < curStr.length() && index < ans.length() && ans.charAt(index) == curStr.charAt(index)) {
                index++;
            }
            ans = ans.substring(0, index);
        }
        return ans;
    }


    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
    }
}
