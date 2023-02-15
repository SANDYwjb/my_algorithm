package com.junbin.algorithm.second.twenty;

/** 300. 最长递增子序列-中等
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 示例：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 动态规划 + 二分查找
 *
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/15下午9:20
 */
public class LongestIncreasingSubsequence {
    // Dynamic programming + Dichotomy.
    class Solution {
        public int lengthOfLIS(int[] nums) {
            int[] tails = new int[nums.length];
            int res = 0;
            for(int num : nums) {
                int i = 0, j = res;
                while(i < j) {
                    int m = (i + j) / 2;
                    if(tails[m] < num) i = m + 1;
                    else j = m;
                }
                tails[i] = num;
                if(res == j) res++;
            }
            return res;
        }
    }
}
