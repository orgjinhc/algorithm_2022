package leetcode.everyday.january;

public class January_25_numberOfMatches {

    public static int numberOfMatches(int n) {
        if (n <= 2) {
            return n - 1;
        }
        if (n % 2 == 0) {
            return n / 2 + numberOfMatches(n / 2);
        } else {
            return (n - 1) / 2 + numberOfMatches((n - 1) / 2 + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(numberOfMatches(7));
    }
}
