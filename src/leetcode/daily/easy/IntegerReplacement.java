package leetcode.daily.easy;


/**
 * https://leetcode-cn.com/problems/integer-replacement/
 */
public class IntegerReplacement {

    public static int integerReplacement(int n) {
        return process(n, 0);
    }

    public static int process(int n, int count) {
        if (n == 1) {
            return count;
        }

        if (n % 2 == 0) {
            return process(n / 2, count + 1);
        } else {
            return Math.min(process(n - 1, count + 1), process(n + 1, count + 1));
        }
    }

    public static void main(String[] args) {
        System.out.println(integerReplacement(65535));
    }
}
