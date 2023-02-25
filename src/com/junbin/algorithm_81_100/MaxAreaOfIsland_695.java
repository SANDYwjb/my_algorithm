package com.junbin.algorithm_81_100;

/**
 * 695. 岛屿的最大面积-中等
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0
 * 思路：深度优先搜索
 * 我们想知道网格中每个连通形状的面积，然后取最大值。
 * 如果我们在一个土地上，以 4 个方向探索与之相连的每一个土地（以及与这些土地相连的土地），那么探索过的土地总数将是该连通形状的面积。
 * 为了确保每个土地访问不超过一次，我们每次经过一块土地时，将统计过的陆地设置为2，防止重复统计。
 * 时间复杂度：O(m×n)，空间复杂度：O(m×n)
 *
 * @author junbin.wang
 * @date 2023/2/25上午10:01
 */
public class MaxAreaOfIsland_695 {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    int gridArea = findGridArea(grid, i, j);
                    maxArea = Math.max(gridArea, maxArea);
                }
            }
        }
        return maxArea;
    }

    private int findGridArea(int[][] grid, int i, int j) {
        int sumArea = 1;
        //将统计过的陆地设置为2，防止重复统计
        grid[i][j] = 2;
        //上面
        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
            sumArea += findGridArea(grid, i - 1, j);
        }
        //下面
        if (i + 1 < grid.length && grid[i + 1][j] == 1) {
            sumArea += findGridArea(grid, i + 1, j);
        }
        //左边
        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
            sumArea += findGridArea(grid, i, j - 1);
        }
        //右边
        if (j + 1 < grid[i].length && grid[i][j + 1] == 1) {
            sumArea += findGridArea(grid, i, j + 1);
        }
        return sumArea;
    }
}
