package com.junbin.algorithm_81_100;

/**
 * 122. 买卖股票的最佳时机 II-简单
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 * 总利润为 4 + 3 = 7 。
 * <p>
 * 思路：贪心，思路特别简单
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 * @author junbin.wang
 * @date 2023/2/25下午7:46
 */
public class MaxProfitII_122 {
    public int maxProfit(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }

        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            // 卖出有利可图
            if (arr[i] > arr[i - 1]) {
                ans += (arr[i] - arr[i - 1]);
            }
        }
        return ans;
    }
}
