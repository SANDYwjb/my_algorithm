package com.junbin.algorithm.first;

/**
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 样例
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4] 输出: 6 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 动态规划解法：
 * <p>
 * 设sum[i]为以第i个元素结尾且和最大的连续子数组。假设对于元素i，所有以它前面的元素结尾的子数组的长度都已经求得，那么以第i个元素结尾且和最大的连续子数组实际上，
 * <p>
 * 要么是以第i-1个元素结尾且和最大的连续子数组加上这个元素，要么是只包含第i个元素，即sum[i]= max(sum[i-1] + a[i], a[i])。
 * <p>
 * 可以通过判断sum[i-1] + a[i]是否大于a[i]来做选择，而这实际上等价于判断sum[i-1]是否大于0。即sum[i-1] 大于0，那就加上a[i]后更大，如果小于0，那就a[i]本身大。这里不考虑a[i]是否大于0，
 * <p>
 * 因为要算的是以第i个元素结尾且连续子数组和。所以，只考虑sum[i-1] 。
 * <p>
 * 由于每次运算只需要前一次的结果，因此并不需要像普通的动态规划那样保留之前所有的计算结果，只需要保留上一次的即可，
 * <p>
 * 因此算法的时间和空间复杂度都很小 。
 *
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/13下午9:35
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {// 动态规划法
        int sum = nums[0];   // 最终的和
        int n = nums[0]; // 每一次遍历的和
        for (int i = 1; i < nums.length; i++) {
            if (n > 0)
                n += nums[i];
            else
                n = nums[i];
            if (sum < n)
                sum = n;
        }
        return sum;
    }
}