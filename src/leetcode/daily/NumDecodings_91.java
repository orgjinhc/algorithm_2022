package leetcode.daily;

/**
 * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * <p>
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * <p>
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * <p>
 * 题目数据保证答案肯定是一个 32 位 的整数。
 * <p>
 * 链接：https://leetcode-cn.com/problems/decode-ways
 */
public class NumDecodings_91 {

    public static int numDecodings(String s) {
        int[] dp = new int[s.toCharArray().length];
        return process(s.toCharArray(), 0, dp);
    }

    /**
     * 处理index位置能有几种映射方式
     * 默认0...index-1已经处理完毕
     *
     * @param chars
     * @param index
     * @return
     */
    public static int process(char[] chars, int index, int[] dp) {

        //  定义边界
        //  case1:已经没有字符需要映射了, 前面的映射可以作为答案返回
        if (index == chars.length) {
            return 1;
        }
        if (dp[index] != 0) {
            return dp[index];
        }
        //  case2:index位置没有映射关系, 当前位置字符可以被映射的答案就是0, 不用继续往下尝试了, 当前这种方案已经不能满足
        if (chars[index] == '0') {
            return 0;
        }
        //  当前位置可以作为一种映射关系返回, 继续看看下一个位置是否可以满足映射条件
        int p1 = process(chars, index + 1, dp);
        if (index + 1 == chars.length) {
            return p1;
        }
        //  可以尝试两个位置一起作为一种映射关系进行尝试, 前提是当前位置+下一位置不能超过映射关系最大边界 26
        int p2 = 0;
        char num = (char) ((chars[index] - '0') * 10 + chars[index + 1] - '0');
        if (num < 27) {
            p2 = process(chars, index + 2, dp);
        }
        dp[index] = p1 + p2;
        return p1 + p2;
    }

    public static void main(String[] args) {
        String s = "226";
        System.out.println(numDecodings(s));
    }
}
