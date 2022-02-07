package leetcode.game;

public class Game_3_mostPoints {


    public static long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            //  当前得分和跳过的题目
            int[] q = questions[i];
            //  当前得分获得后, 下一次可以获得分数是
            int j = i + q[1] + 1;

            dp[i] = Math.max(dp[i + 1], q[0] + (j < n ? dp[j] : 0));
        }
        return dp[0];
    }


    public static void main(String[] args) {
        int[][] questions = {{28, 1}};
        System.out.println(mostPoints(questions));
    }
}
