package leetcode.top_200.medium;

/**
 * 给定一个数组 prices ，其中prices[i] 是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * <p>
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例3:
 * <p>
 * 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 * <p>
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 */
public class Top_122_MaxProfit {

    public static int maxProfit(int[] prices) {
        //  默认价格最低时刻的金额为第一天
        int minPrice = prices[0];
        //  默认最大收益为0
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            //  如果当前时刻的股票金额小于 之前最小金额, 更新最小金额
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (i < prices.length - 1 && prices[i] > prices[i + 1]) { //  当天的价格大于明天的股票价格, 及时出手套现, 套现后的最小价格更新为今天, 如果之后的价格小于今天就更新最小价格
                //  最大收益就是当天的价格 - 之前买入的价格
                maxProfit += prices[i] - minPrice;
                minPrice = prices[i];
            } else if (i == prices.length - 1 && prices[i] - minPrice > 0) //   最后是最后一天了, 并且当天的价格 - 之前买入的价格只要有收益就卖掉
                maxProfit += prices[i] - minPrice;
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices));
    }

}
