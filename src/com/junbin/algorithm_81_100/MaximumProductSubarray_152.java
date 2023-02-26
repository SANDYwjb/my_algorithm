package com.junbin.algorithm_81_100;

/**
 * 152. 乘积最大子数组-中等
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。子数组 是数组的连续子序列。
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * 思路：动态规划
 * 遍历数组时计算当前最大值，不断更新。令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
 * 由于存在负数，那么会导致最大的变最小的(即之前的imax是最大值，但是num[i]是负数，乘积后便最小)，最小的变最大的(两个负数相乘，就变正数)。
 * 因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])。
 * 当负数出现时则imax与imin进行交换再进行下一步计算，为什么要进行交换，因为和负数相乘会改变正负关系。细品细品。。。
 * 时间复杂度：O(n)
 *
 * @author junbin.wang
 * @date 2023/2/25上午10:40
 */
public class MaximumProductSubarray_152 {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }
}
