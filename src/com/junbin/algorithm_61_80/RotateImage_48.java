package com.junbin.algorithm_61_80;

/**
 * 48. 旋转图像-中等
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * 思路：
 * 建议画图看。
 * 我们可以得到规律：对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在 倒数 第 i 列的第 j 个位置。
 * 我们将其翻译成代码。由于矩阵中的行列从 0 开始计数，因此对于矩阵中的元素 matrix[row][col]，在旋转后，它的新位置为 matrixnew[col][n−row−1]
 * 即matrix[row][col] -> matrixnew[col][n−row−1]
 * 但我们不能直接替换，因为会被覆盖掉，因此我们可以考虑用一个临时变量 temp暂存 matrix[col][n−row−1]的值。
 * 那么 matrix[col][n−row−1]经过旋转操作之后会到哪个位置呢？我们还是使用方法一中的关键等式，不过这次，我们需要将row=col，col =n−row−1代入等式，
 * 即matrix[col][n−row−1] -> matrix[n−row−1][n−col−1]，同样使用temp。
 * 我们再重复一次之前的操作，看matrix[n−row−1][n−col−1]旋转后在哪个位置，同理可得：
 * matrix[n−row−1][n−col−1] -> matrix[n−col−1][row]
 * 再来看matrix[n−col−1][row]旋转后的位置，即：
 * matrix[n−col−1][row] -> matrix[row][col]，回到了最初的起点。这四项处于一个循环中，并且每一项旋转后的位置就是下一项所在的位置！
 * 因此我们可以只使用一个临时变量 temp完成这四项的原地交换
 * <p>
 * 当我们知道了如何原地旋转矩阵之后，还有一个重要的问题在于：我们应该枚举哪些位置 (row,col)进行上述的原地交换操作呢？由于每一次原地交换四个位置，
 * 因此：
 * 1. 当 n 为偶数时，我们需要枚举 n^2 / 4 = (n/2)×(n/2)个位置，可以将该图形分为四块，保证了不重复、不遗漏
 * 2. 当 n 为奇数时，由于中心的位置经过旋转后位置不变，我们需要枚举 (n^2-1) / 4 = ((n-1)/2)×((n+1)/2)个位置，需要换一种划分的方式
 * 时间复杂度：O(N^2)
 * 空间复杂度：O(1)
 *
 * @author junbin.wang
 * @date 2023/2/23上午8:40
 */
public class RotateImage_48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                // 第一个位置，需要防止四次循环中最后一个位置循环的元素，和上面的推论是反着来的
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
}
