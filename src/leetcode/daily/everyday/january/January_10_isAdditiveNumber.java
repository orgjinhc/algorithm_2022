package leetcode.daily.everyday.january;

public class January_10_isAdditiveNumber {

    public static boolean isAdditiveNumber(String num) {
        int N = num.length();
        char firstStartValue = num.charAt(0);
        //  穷举第二个数的开始位置, 等价于穷举第一个数的结束位置, 因为第一个数的开始位置已经固定
        for (int secondStart = 1; secondStart < N - 1; ++secondStart) {
            if (firstStartValue == '0' && secondStart != 1) {
                break;
            }
            //  穷举第二个数的结束位置
            for (int secondEnd = secondStart; secondEnd < N - 1; ++secondEnd) {
                if (num.charAt(secondStart) == '0' && secondStart != secondEnd) {
                    break;
                }
                if (valid(secondStart, secondEnd, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 根据第二个数的开始和结束位置推导出第一个数的开始和结束位置
     * 再根据前两个数的位置推导出第三个数的大小和位置
     *
     * @param secondStart
     * @param secondEnd
     * @param num
     * @return
     */
    public static boolean valid(int secondStart, int secondEnd, String num) {
        int N = num.length();
        int firstStart = 0;
        int firstEnd = secondStart - 1;
        while (secondEnd <= N - 1) {
            String third = stringAdd(num, firstStart, firstEnd, secondStart, secondEnd);

            //  第三个数的开始和结束依次类推
            int thirdStart = secondEnd + 1;
            int thirdEnd = secondEnd + third.length();
            if (thirdEnd >= N || !num.substring(thirdStart, thirdEnd + 1).equals(third)) {
                break;
            }
            if (thirdEnd == N - 1) {
                return true;
            }
            firstStart = secondStart;
            firstEnd = secondEnd;
            secondStart = thirdStart;
            secondEnd = thirdEnd;
        }
        return false;
    }

    /**
     * 两数按位相加
     *
     * @param s
     * @param firstStart
     * @param firstEnd
     * @param secondStart
     * @param secondEnd
     * @return
     */
    public static String stringAdd(String s, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        StringBuffer third = new StringBuffer();
        int carry = 0;
        int cur = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart || carry != 0) {
            cur = carry;
            if (firstEnd >= firstStart) {
                cur += s.charAt(firstEnd) - '0';
                --firstEnd;
            }
            if (secondEnd >= secondStart) {
                cur += s.charAt(secondEnd) - '0';
                --secondEnd;
            }
            carry = cur / 10;
            cur %= 10;
            third.append((char) (cur + '0'));
        }
        third.reverse();
        return third.toString();
    }

    public static void main(String[] args) {
        String num = "199100199";
        System.out.println(isAdditiveNumber(num));
    }
}
