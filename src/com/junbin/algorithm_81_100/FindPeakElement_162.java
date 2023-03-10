package com.junbin.algorithm_81_100;

/**
 * 162. 寻找峰值 - 中等
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。你必须实现时间复杂度为 O(log n) 的算法来解决此问题。对于所有有效的i都有nums[i] != nums[i + 1]
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * 思路1：由于题目保证了 nums[i] != nums[i + 1]，那么数组 nums中最大值两侧的元素一定严格小于最大值本身。因此，最大值所在的位置就是一个可行的峰值位置。
 * 我们对数组 nums进行一次遍历，找到最大值对应的位置即可。时间复杂度为O(n)，不符合要求
 * 思路2：二分查找
 * 我们应该注意到：只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值
 * 根据上述结论，我们就可以使用二分查找找到峰值。查找时，左指针 l，右指针 r，以其保持左右顺序为循环条件
 * 根据左右指针计算中间位置 m，并比较 m 与 m+1 的值，如果 m 较大，则左侧存在峰值，r = m，如果 m + 1 较大，则右侧存在峰值，l = m + 1
 *
 * @author junbin.wang
 * @date 2023/2/25上午9:37
 */
public class FindPeakElement_162 {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
