package com.junbin.algorithm.first.twenty;

/**
 * 买卖股票的最佳时机
 * 给定一个数组，它的第i个元素是一支给定股票第i天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 贪心算法解决：分别找到价格最低和最高的一天，低进高出，注意最低的一天要在最高的一天之前。
 *
 * @author junbin.wang
 * @date 2023/2/14下午9:14
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {
//        int res = 0;
//        int min = Integer.MAX_VALUE;
//        // 遍历一次就行
//        for (int i = 0; i < prices.length; i++) {
//            //下面两行代码的前后顺序不能倒过来，因为不能当天买当天卖，不然也只能是赚0
//            // 选择res和prices[i] - min中更大的。
//            res = res > prices[i] - min ? res : prices[i] - min;
//            // 选择min和当前天价格更小的那个
//            min = min < prices[i] ? min : prices[i];
//        }
//        return res;

        if (prices == null || prices.length < 1) {
            return 0;
        }
        int min = prices[0];
        int profit = 0;
        // 第i天的价格可以看作是买入价也可以看作是卖出价
        for (int i = 1; i < prices.length; i++) {
            // 找到更低的买入价
            if (min > prices[i]) {
                // 更新买入价
                min = prices[i];
            }
            // 当天的价格不低于买入价
            else {
                // 如果当天卖出的价格比之前卖出的价格高
                if (profit < prices[i] - min) {
                    // 更新卖出价
                    profit = prices[i] - min;
                }
            }
        }

        return profit;
    }
}
