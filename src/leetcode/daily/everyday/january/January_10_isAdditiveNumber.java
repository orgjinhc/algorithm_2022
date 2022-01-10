package leetcode.daily.everyday.january;

public class January_10_isAdditiveNumber {

    public static boolean isAdditiveNumber(String num) {
        int one = num.charAt(0) - 48;
        for (int i = 1; i < num.length() - 1; i++) {
            if (num.charAt(i + 1) - 48 != one + num.charAt(i) - 48) {
                return false;
            }
            one = num.charAt(i) - 48;
        }
        return true;
    }

    public static void main(String[] args) {
        String num = "112358";
        System.out.println(isAdditiveNumber(num));

        String[] split = num.split("");
    }
}
