package com.junbin.algorithm.second.twenty;

/**
 * 200. 岛屿数量-中等
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向 和/或 竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 示例1：
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * <p>
 * 示例2：
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * 在 LeetCode 中，「岛屿问题」是一个系列系列问题。都可以用DFS（深度优先搜索）解决.
 *
 * @author junbin.wang
 * @date 2023/2/15下午9:01
 */
public class NumberOfIslands_200 {
    /**
     * 使用感染的方法
     * <p>
     * 1）从左往右、从上往下挨个遍历这个二维数组，当值为 '1' 时，将其附近上下左右一片的 '1' 修改为2
     * 2）每修改一次，就将岛的数量 +1，最后返回的即是岛屿数量
     * <p>
     * 时间复杂度O(m*n)
     */
    public static class Solution1 {
        public static int numIslands(char[][] grid) {
            int result = 0;
            // 挨个遍历整个二维数组
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    // 此时位置上是‘1’，则修改周围一片的‘1’，同时岛的数量加1
                    if (grid[i][j] == '1') {
                        result++;
                        infect(grid, i, j);
                    }
                }
            }
            return result;
        }

        /**
         * 从（i，j）位置开始，将周围连成一片的‘1’字符修改为2的ASCII
         */
        private static void infect(char[][] grid, int i, int j) {
            if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] != '1') {
                return;
            }
            grid[i][j] = 2;
            infect(grid, i - 1, j);
            infect(grid, i + 1, j);
            infect(grid, i, j - 1);
            infect(grid, i, j + 1);
        }
    }

    /**
     * DFS解法，看微信公众号文章
     */
    public static class Solution2 {
        public int islandPerimeter(int[][] grid) {
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    if (grid[r][c] == 1) {
                        // 题目限制只有一个岛屿，计算一个即可
                        return dfs(grid, r, c);
                    }
                }
            }
            return 0;
        }

        int dfs(int[][] grid, int r, int c) {
            // 函数因为「坐标 (r, c) 超出网格范围」返回，对应一条黄色的边
            if (!inArea(grid, r, c)) {
                return 1;
            }
            // 函数因为「当前格子是海洋格子」返回，对应一条蓝色的边
            if (grid[r][c] == 0) {
                return 1;
            }
            // 函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系
            if (grid[r][c] != 1) {
                return 0;
            }
            grid[r][c] = 2;
            return dfs(grid, r - 1, c)
                    + dfs(grid, r + 1, c)
                    + dfs(grid, r, c - 1)
                    + dfs(grid, r, c + 1);
        }

        // 判断坐标 (r, c) 是否在网格中
        boolean inArea(int[][] grid, int r, int c) {
            return 0 <= r && r < grid.length
                    && 0 <= c && c < grid[0].length;
        }
    }
}
