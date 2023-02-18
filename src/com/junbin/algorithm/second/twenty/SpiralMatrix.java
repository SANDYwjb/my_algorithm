package com.junbin.algorithm.second.twenty;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵-中等
 * 给你一个 m 行 n 列的矩阵 ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 按层模拟
 * 可以将矩阵看成若干层，首先输出最外层的元素，其次输出次外层的元素，直到输出最内层的元素。
 * 定义矩阵的第 k 层是到最近边界距离为 k 的所有顶点。例如，下图矩阵最外层元素都是第 1 层，次外层元素都是第 2 层，剩下的元素都是第 3 层。
 *
 * @author junbin.wang
 * @date 2023/2/15下午9:12
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        // 上下代表行，左右代表列
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            // top没变，只遍历这一行，从左往右遍历每一列
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            // right没变，只遍历这一列，从上往下遍历每一行，记得top+1，不然会重复遍历
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            // 这里需要在判断一次，并且不能相等，否则会重复遍历到
            if (left < right && top < bottom) {
                // bottom没变，只遍历这一行，从右往左遍历每一列
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                // left没变，只遍历这一列，从下往上遍历每一行
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }
}
