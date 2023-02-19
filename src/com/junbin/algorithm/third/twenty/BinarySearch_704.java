package com.junbin.algorithm.third.twenty;

/**
 * 704. 二分查找-简单
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，
 * 如果目标值存在返回下标，否则返回 -1。
 * 思路：
 * 在升序数组 nums 中寻找目标值 target，对于特定下标 i，比较 nums[i]和 target 的大小：
 * 1. 如果 nums[i]=target，则下标 i 即为要寻找的下标；
 * 2. 如果 nums[i]>target，则 target只可能在下标 i 的左侧；
 * 3. 如果 nums[i]<target，则 target 只可能在下标 i 的右侧。
 * 基于上述事实，可以在有序数组中使用二分查找寻找目标值。
 * 二分查找的做法是，定义查找的范围 [left,right]，初始查找范围是整个数组。每次取查找范围的中点 mid，比较 nums[mid] 和 target的大小，
 * 如果相等则 mid 即为要寻找的下标，如果不相等则根据 nums[mid]和 target的大小关系将查找范围缩小一半。
 * 由于每次查找都会将查找范围缩小一半，因此二分查找的时间复杂度是 O(log⁡n)，其中 n 是数组的长度。
 * 二分查找的条件是查找范围不为空，即 left≤right。如果 target在数组中，二分查找可以保证找到 target，返回 target在数组中的下标。
 * 如果 target不在数组中，则当 left>right时结束查找，返回 −1。
 *
 * @author junbin.wang
 * @date 2023/2/19下午8:22
 */
public class BinarySearch_704 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
