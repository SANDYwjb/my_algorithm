package com.junbin.algorithm.third.twenty;

import java.util.Arrays;

/**
 * 322. 零钱兑换-中等
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * 思路：动态规划
 * 我们采用自下而上的方式进行思考。仍定义 dp[i] 为组成金额 i 所需最少的硬币数量，
 * 定义dp方程：
 * 假设i表示遍历到amount的第i位，这个时候表示要得到i块钱，同时假设只有一种硬币值coin,这样得到i块钱的硬币个数就非常清晰，
 * 用得到i-coin 的硬币个数加1就行了，即dp[i] = dp[i-coin] + 1.(比如i=10，coin为5，则得到i块钱的硬币个数：10-5=5，即得到5块钱的硬币个数+1就行)
 * 因为有多种硬币值所以我们需要对每种硬币都这样求解一次，然后从求解的结果中拿到最小值就行了，从而得到如下的方程：
 * dp[i] = Math.min(dp[i-coins[j]]+1, dp[i] );
 * <p>
 * 时间复杂度：O(Sn)，其中 S 是金额，n 是面额数
 * 空间复杂度：O(S)。数组 dp 需要开长度为总金额 S 的空间。
 *
 * @author junbin.wang
 * @date 2023/2/19下午12:08
 */
public class CoinChange_322 {
    public int coinChange(int[] coins, int amount) {
        // 数组内每个值表示需要的硬币个数，而下标表示amount值，加1的原因是硬币最小面值是1，并且amount有可能是0
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        // 遍历每种金额
        for (int i = 1; i <= amount; i++) {
            // 遍历每种币种
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
