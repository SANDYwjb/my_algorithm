package com.junbin.algorithm_81_100;

/**
 * 283. 移动零-简单
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * <p>
 * 思路：双指针
 * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，
 * 同时左指针右移。
 * 注意到以下性质：
 * 1. 左指针左边均为非零数；
 * 2. 右指针左边直到左指针处均为零。
 * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
 *
 * @author junbin.wang
 * @date 2023/2/26上午9:23
 */
public class MoveZeroes_283 {
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            // 非0，交换数据，左右指针都往右移
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            // 0，右指针右移
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
