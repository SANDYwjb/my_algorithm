package com.junbin.algorithm.second.twenty;

/**
 * 1143. 最长公共子序列-中等
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * <p>
 * 思路：动态规划 最长公共子序列问题是典型的二维动态规划问题。
 * 假设字符串 text1和 text2的长度分别为 m 和 n，创建 m+1行 n+1列的二维数组dp，其中 dp[i][j]表示 text1[0:i]和 text2[0:j]的最长公共子序列的长度。
 * 考虑动态规划的边界情况：
 * 1. 当i=0时，text1[0:i]为空，空字符串和任何字符串的最长公共子序列的长度都是 0，因此对任意 0≤j≤n，有 dp[0][j]=0；
 * 2. 当j=0时，text2[0:j]为空，同理可得，对任意 0≤i≤m，有 dp[i][0]=0。
 * 因此动态规划的边界情况是：当 i=0或 j=0时，dp[i][j]=0。
 * 当 i>0 且 j>0时，考虑 dp[i][j]的计算：
 * 1. 当 text1[i−1]=text2[j−1]时，将这两个相同的字符称为公共字符，即text1[0:i−1]和 text2[0:j−1]的最长公共子序列，
 * 再增加一个字符（即公共字符）即可得到 text1[0:i]和 text2[0:j]的最长公共子序列，因此 dp[i][j]=dp[i−1][j−1]+1。
 * 2. 当 text1[i−1]≠text2[j−1]时，考虑以下两项：
 * a. text1[0:i−1]和 text2[0:j]的最长公共子序列；
 * b. text1[0:i]和 text2[0:j−1]的最长公共子序列。
 * 要得到 text1[0:i]和 text2[0:j]的最长公共子序列，应取两项中的长度较大的一项，因此 dp[i][j]=max⁡(dp[i−1][j],dp[i][j−1])。
 * <p>
 * 时间复杂度：O(mn)，其中 m 和 n 分别是字符串 text1和 text2的长度。
 * 空间复杂度：O(mn)，其中 m 和 n 分别是字符串 text1和 text2的长度。因为要创建dp空间
 *
 * @author junbin.wang
 * @date 2023/2/18下午1:40
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
