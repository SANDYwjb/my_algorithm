package com.junbin.algorithm.first.twenty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15.三数之和-中等
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 * 算法流程：
 * 特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []。
 * 对数组进行排序。
 * 遍历排序后数组：
 * a. 若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
 * b. 对于重复元素：跳过，避免出现重复解
 * c. 令左指针 L=i+1，右指针 R=n-1，当 L<R 时，执行循环：
 * (1) 当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R 移 到下一位置，寻找新的解；
 * (2) 若和大于 0，说明 nums[R] 太大，R 左移；
 * (3) 若和小于 0，说明 nums[L] 太小，L右移.
 *
 * @author junbin.wang
 * @date 2023/2/13下午9:33
 */
public class ThreeSum_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        //排序
        Arrays.sort(nums);
        //双指针
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            //排序后如果第一个（最小的）大于0，则和必然大于0，如果最后一个（最大的）小于0同理
            if (nums[i] > 0 || nums[len - 1] < 0) {
                return lists;
            }
            //如果当前元素和它前面的元素相同则说明处理过同样的值了，需要跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int curr = nums[i];
            int L = i + 1, R = len - 1;
            while (L < R) {
                int tmp = curr + nums[L] + nums[R];
                if (tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    // 这里如果下一位重复的话，即使算两种，还是会和上一种答案重复，所以需要去掉
                    while (L < R && nums[L + 1] == nums[L]) {
                        ++L;
                    }
                    while (L < R && nums[R - 1] == nums[R]) {
                        --R;
                    }
                    ++L;
                    --R;
                } else if (tmp < 0) {
                    ++L;
                } else {
                    --R;
                }
            }
        }
        return lists;
    }
}
