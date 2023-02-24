package com.junbin.algorithm_61_80;

/**
 * 221. 最大正方形-中等
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * 思路：动态规划
 * 可以使用动态规划降低时间复杂度。我们用 dp(i,j)表示以 (i,j)为右下角，且只包含 1 的正方形的边长最大值。如果我们能计算出所有 dp(i,j) 的值，
 * 那么其中的最大值即为矩阵中只包含 1 的正方形的边长最大值，其平方即为最大正方形的面积。
 * 那么如何计算 dp 中的每个元素值呢？对于每个位置 (i,j)，检查在矩阵中该位置的值：
 * 1. 如果该位置的值是 0，则 dp(i,j)=0，因为当前位置不可能在由 1 组成的正方形中；
 * 2. 如果该位置的值是 1，则 dp(i,j)的值由其上方、左方和左上方的三个相邻位置的 dp值决定。具体而言，当前位置的元素值等于三个相邻位置的元素中的最小值加1，
 * 状态转移方程如下：
 * dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
 * 如果读者对这个状态转移方程感到不解，可以参考 1277. 统计全为 1 的正方形子矩阵的官方题解，其中给出了详细的证明。
 * 此外，还需要考虑边界条件。如果 i 和 j 中至少有一个为 0，则以位置 (i,j) 为右下角的最大正方形的边长只能是 1，因此 dp(i,j)=1。
 * 看不懂可以看resource中的一张示例图
 * 时间复杂度：O(mn)，其中 m 和 n 是矩阵的行数和列数
 * 空间复杂度：O(mn)，其中 m 和 n 是矩阵的行数和列数
 *
 * @author junbin.wang
 * @date 2023/2/24上午8:36
 */
public class MaximalSquare_221 {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        // 这里为什么数组长度不用+1
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
}
