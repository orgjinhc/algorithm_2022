package leetcode.top_200.medium;

/**
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * <p>
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * <p>
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 * <p>
 * countAndSay(1) = "1"
 * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 * <p>
 * 链接：https://leetcode-cn.com/problems/count-and-say
 */
public class Top_38_countAndSay {

    public static String countAndSay(int n) {
        //  定义边界
        if (n < 1) {
            return "";
        }
        if (n == 1) {
            return "1";
        }
        //  递归, 求出N-1上的字符
        char[] last = countAndSay(n - 1).toCharArray();
        int N = last.length;
        StringBuilder ans = new StringBuilder();
        int times = 1;
        for (int i = 1; i < N; i++) {
            if (last[i - 1] == last[i]) {
                times++;
            } else {
                ans.append(times);
                ans.append(last[i - 1]);
                times = 1;
            }
        }
        ans.append(times);
        ans.append(last[N - 1]);
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(10));
    }
}