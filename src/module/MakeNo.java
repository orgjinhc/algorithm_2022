package module;

public class MakeNo {


    public static int[] process(int M) {
        if (M < 2) {
            return new int[]{1, 3};
        }

        int[] dp = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            if (i % 3 == 0) {
                dp[i] = dp[i - 1] * 2;
                continue;
            }
            dp[i] = dp[i - 1] + i;
        }

        int[] ans = new int[M];
        int index = 0;
        for (int i = 1; i < dp.length; i++) {
            ans[index++] = dp[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ans = process(10);

        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
}
