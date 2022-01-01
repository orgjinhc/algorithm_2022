package leetcode.daily.easy;

public class Daily_338_CountBits {

    /**
     * Brian Kernighan
     *  x = x & (x−1)，该运算将 x 的二进制表示的最后一个 1 变成 0。因此，对 x 重复该操作，直到 x 变成 0
     *
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = Integer.bitCount(i);
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(Integer.bitCount(5));
    }
}
