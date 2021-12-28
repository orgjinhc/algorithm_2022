package leetcode.hot_100;

/**
 * 给定一个数组 prices ，它的第i 个元素prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 */
public class Hot_121_MaxProfit_121 {

    public static int maxProfit2(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    public static int maxProfit(int[] prices) {
        int N = prices.length;
        if (N < 2) {
            return 0;
        }

        //  右边最大max[]
        int[] rightMax = new int[N];
        rightMax[N - 1] = prices[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], prices[i]);
        }

        //  ansIndex[0] 买入 ansIndex[1] 卖出
        int[][] ansIndex = new int[N][2];
        int min = 0;
        for (int i = 0; i < N; i++) {
            ansIndex[i][0] = prices[i];
            ansIndex[i][1] = rightMax[i];
        }

        int ans = 0;
        for (int i = 0; i < ansIndex.length; i++) {
            if (ansIndex[i][0] != ansIndex[i][1]) {
                ans = Math.max(ansIndex[i][1] - ansIndex[i][0], ans);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {7,1,5,3,6,4};
        System.out.println(maxProfit(nums));
    }
}
