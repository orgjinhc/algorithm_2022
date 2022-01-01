package leetcode.daily.easy;

public class Daily_461_HammingDistance {

    public static int hammingDistance(int x, int y) {
        int s = x ^ y;
        int ans = 0;
        while (s != 0) {
            ans += s & 1;
            s >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(19, 14));
    }

}
