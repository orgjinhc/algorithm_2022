package leetcode.daily.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Daily_120_minimumTotal {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        //  自顶向下开始计算, 第一行、第一列位置 - 特殊处理
        dp[0][0] = triangle.get(0).get(0);

        //  标准计算逻辑, 从第二行开始计算
        for (int i = 1; i < n; ++i) {
            //  最左边界情况, j = 0 case
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
            //  默认从第 2 列开始到 j-1 列的通用计算逻辑
            for (int j = 1; j < i; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + triangle.get(i).get(j);
            }
            //  最右边界情况, j = i case
            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }

        int minTotal = dp[n - 1][0];
        for (int j = 1; j < n; ++j) {
            minTotal = Math.min(minTotal, dp[n - 1][j]);
        }
        return minTotal;
    }


    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>(Arrays.asList(Arrays.asList(-1), Arrays.asList(2, 3), Arrays.asList(1, -1, 3)));
//        triangle = new ArrayList<>(Arrays.asList(Arrays.asList(-20)));
        System.out.println(minimumTotal(triangle));
    }
}
