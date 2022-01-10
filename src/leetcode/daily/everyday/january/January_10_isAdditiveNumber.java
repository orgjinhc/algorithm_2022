package leetcode.daily.everyday.january;

public class January_10_isAdditiveNumber {

    /**
     * 此题使用三种技巧
     * 1.暴力尝试, 三个数字进行相加逻辑, 只需要通过第二个数可以获得第一个数和第三个数(核心逻辑)
     * 2.字符实现十进制相加
     * 3.字符串比较和位置交换
     *
     * @param num
     * @return
     */
    public static boolean isAdditiveNumber(String num) {
        int N = num.length();
        for (int secondStart = 1; secondStart < N - 1; secondStart++) {
            //  只允许首位是0的情况, 其余情况不允许
            if (num.charAt(0) == '0' && secondStart > 1) {
                continue;
            }
            //  暴力尝试第二个数的结束位置, 直到末尾位置
            for (int secondEnd = secondStart; secondEnd < N - 1; secondEnd++) {
                //  只允许首位是0的情况, 其余情况不允许
                if (num.charAt(secondStart) == '0' && secondEnd > secondStart) {
                    break;
                }
                if (isValid(num, secondStart, secondEnd)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 字符串比对
     * firstStart = 0
     * firstEnd = secondStart - 1
     * secondStart = 递增
     * secondEnd - 递增
     * thirdStart = secondEnd + 1
     * thirdEnd = secondEnd + length
     *
     * @param num
     * @param secondStart
     * @param secondEnd
     * @return
     */
    private static boolean isValid(String num, int secondStart, int secondEnd) {
        int N = num.length();
        int firstStart = 0;
        int firstEnd = secondStart - 1;
        while (secondEnd < N) {
            String third = obtainForThirdNumberByAdditiveNumber(num, firstStart, firstEnd, secondStart, secondEnd);
            int thirdStart = secondEnd + 1;
            int thirdEnd = secondEnd + third.length();

            //  第三个数的结束位置已经越界 or 前两个数相加的结果和数字字符串的位置上的数不等
            if (thirdEnd >= N || !num.substring(thirdStart, thirdEnd + 1).equals(third)) {
                return false;
            }
            //  第三个数的位置正好是右边界情况, 直接返回true
            if (thirdEnd == N - 1) {
                return true;
            }

            //  继续验证下一个位置的数
            firstStart = secondStart;
            firstEnd = secondEnd;
            secondStart = thirdStart;
            secondEnd = thirdEnd;
        }
        return true;
    }

    /**
     * 字符串相加实现
     *
     * @param num
     * @param firstStart
     * @param firstEnd
     * @param secondStart
     * @param secondEnd
     * @return
     */
    private static String obtainForThirdNumberByAdditiveNumber(String num, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart || carry != 0) {
            int cur = 0;
            if (firstEnd >= firstStart) {
                cur += num.charAt(firstEnd) - '0';
                firstEnd--;
            }
            if (secondEnd >= secondStart) {
                cur += num.charAt(secondEnd) - '0';
                secondEnd--;
            }
            int sum = cur + carry;
            carry = sum / 10;
            sb.append(sum % 10);
        }
        sb.reverse();
        return sb.toString();
    }

    public static void main(String[] args) {
        String num = "12012122436";
        System.out.println(isAdditiveNumber(num));
    }
}