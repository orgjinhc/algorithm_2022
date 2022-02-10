package base.recursive.classic.dp.从左到右模型;

/**
 * 从左往右模型
 * <p>
 * 1  对应 A
 * 2  对应 B
 * 26 对应 Z
 */
public class 数字转字符问题 {

    public static void main(String[] args) {

    }

    public static int number(String str) {
        return process(str, 0);
    }

    /**
     * 暴力递归函数
     *
     * @param str   原始字符串
     * @param index str[0...index-1]位置已经找到了转换方式, 当前来到index位置, 尝试index...N-1上找到转换方式
     * @return
     */
    public static int process(String str, int index) {
        int N = str.length();
        //  证明可以找到一种转换方式
        if (index == N) {
            return 1;
        }
        //  当前位置是0, 无法转换
        if (str.charAt(index) == '0') {
            return 0;
        }
        //  开始进行递归方法实现
        //  第一种方法直接尝试转换当前位置的数字, 递归调用下一个位置上的数字看看是否能转换
        int ways = process(str, index + 1);
        //  第二种方法查看当前位置+下一位置组合得到一个小于27的数字, 或下一位置不越界
        if (index + 1 < N && (str.charAt(index) - '0' + str.charAt(index + 1) - '0') < 27) {
            ways += process(str, index + 2);
        }
        return ways;
    }


}
