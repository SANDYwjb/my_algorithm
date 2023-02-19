package com.junbin.algorithm_21_40;

import java.util.Arrays;

/**
 * 300. 最长递增子序列-中等
 * 给你一个整数数组nums，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 示例：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 注意「子序列」和「子串」这两个名词的区别，子串一定是连续的，而子序列不一定是连续的
 * 思路：
 * 动态规划 + 二分查找
 *
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/15下午9:20
 */
public class LongestIncreasingSubsequence_300 {
    /**
     * 动态规划解法，时间复杂度 O(N^2)
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度。
        int[] dp = new int[nums.length];
        // 最终结果（子序列的最大长度）应该是 dp 数组中的最大值。
        int res = 0;
        // 动态规划的base case：dp数组应该全部初始化为 1
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // 对于dp[i]来说，既然是递增子序列，我们只要找到前面那些结尾比num[i]小的子序列，然后把num[i]接到最后，
                // 就可以形成一个新的递增子序列，而且这个新的子序列长度加一。
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // Dynamic programming + Dichotomy. 时间复杂度为 O(NlogN)，用扑克牌分堆的思想，具体看公众号文章
    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        // 牌堆数初始化为0
        int piles = 0;
        // poker是要处理的扑克牌，即放到哪一堆中
        for (int poker : nums) {
            int left = 0, right = piles;
            while (left < right) {
                int m = (left + right) / 2;
                if (tails[m] < poker) {
                    left = m + 1;
                } else {
                    right = m;
                }
            }
            tails[left] = poker;
            if (piles == right) {
                piles++;
            }
        }
        return piles;
    }
}
