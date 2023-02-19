package com.junbin.algorithm_41_60;

/**
 * 69. x 的平方根 - 简单
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 思路：
 * 二分查找
 * 由于 x 平方根的整数部分 ans是满足 k^2 ≤x 的最大 k 值，因此我们可以对 k 进行二分查找，从而得到答案。
 * 二分查找的下界为 0，上界可以粗略地设定为 x。在二分查找的每一步中，我们只需要比较中间元素 mid的平方与 x 的大小关系，
 * 并通过比较的结果调整上下界的范围。由于我们所有的运算都是整数运算，不会存在误差，因此在得到最终的答案 ans后，
 * 也就不需要再去尝试 ans+1了。
 * 时间复杂度：O(log⁡x)，即为二分查找需要的次数。
 * 空间复杂度：O(1)。
 *
 * @author junbin.wang
 * @date 2023/2/18下午3:49
 */
public class SqrtX_69 {
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
