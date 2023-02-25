package com.junbin.algorithm_81_100;

/**
 * 240. 搜索二维矩阵 II- 中等
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。每列的元素从上到下升序排列。
 * 思路：Z 字形查找
 * 我们可以从矩阵 matrix的右上角 (0,n−1)进行搜索。在每一步的搜索过程中，如果我们位于位置 (x,y)，那么我们希望在以 matrix的左下角为左下角、
 * 以 (x,y)为右上角的矩阵中进行搜索，即行的范围为 [x,m−1]，列的范围为 [0,y]：
 * 1. 如果 matrix[x,y]=target，说明搜索完成；
 * 2. 如果 matrix[x,y]>target，由于每一列的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 y 列的元素都是严格大于 target的，
 * 因此我们可以将它们全部忽略，即将 y 减少 1；
 * 3. 如果 matrix[x,y]<target，由于每一行的元素都是升序排列的，那么在当前的搜索矩阵中，所有位于第 x 行的元素都是严格小于 target\的，
 * 因此我们可以将它们全部忽略，即将 x 增加 1。
 * 在搜索的过程中，如果我们超出了矩阵的边界，那么说明矩阵中不存在 target。
 * 时间复杂度：O(m+n)
 * 空间复杂度：O(1)
 *
 * @author junbin.wang
 * @date 2023/2/25上午8:46
 */
public class SearchA2dMatrixII_240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 从右上角开始
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            if (matrix[x][y] > target) {
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }
}
