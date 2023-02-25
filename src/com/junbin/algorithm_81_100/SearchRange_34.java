package com.junbin.algorithm_81_100;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置-中等
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 思路：二分查找
 * 直观的思路肯定是从前往后遍历一遍。用两个变量记录第一次和最后一次遇见 target的下标，但这个方法的时间复杂度为 O(n)，没有利用到数组升序排列的条件。
 * 由于数组已经排序，因此整个数组是单调递增的，我们可以利用二分法来加速查找的过程。
 * 考虑target开始和结束位置，其实我们要找的就是数组中「第一个等于 target的位置」（记为leftIdx）和「第一个大于target的位置减一」（记为rightIdx）。
 * 二分查找中，寻找 leftIdx即为在数组中寻找第一个等于 target的下标，寻找 rightIdx即为在数组中寻找第一个大于 target的下标，然后将下标减一。
 * 时间复杂度： O(log⁡n)
 * 空间复杂度：O(1)
 *
 * @author junbin.wang
 * @date 2023/2/25上午9:02
 */
public class SearchRange_34 {
    public int[] searchRange(int[] nums, int target) {
        //数组中没有元素，特判一下
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        //找到大于等于target的第一个位置
        int leftIndex = binarySearch(nums, target);
        //找到大于等于target+1的下一个位置
        int rightIndex = binarySearch(nums, target + 1) - 1;
        if (leftIndex <= rightIndex && rightIndex < nums.length && nums[leftIndex] == target && nums[rightIndex] == target) {
            return new int[]{leftIndex, rightIndex};
        }
        return new int[]{-1, -1};

    }

    public int binarySearch(int[] nums, int target) {
        //初始条件，设置左边界和右边界
        int left = 0, right = nums.length - 1;
        //循环条件
        while (left <= right) {
            int mid = (left + right) / 2;
            //大于等于target
            if (nums[mid] >= target) {
                //从mid-1开始找
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //找大于等于的数，返回left
        return left;
    }
}
