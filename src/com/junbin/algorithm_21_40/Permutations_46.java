package com.junbin.algorithm_21_40;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 46. 全排列-中等
 * 给定一个不含重复数字的数组nums，返回其所有可能的全排列 。你可以按任意顺序返回答案。
 * 示例：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 回溯法解决
 * 采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当它通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，
 * 它将取消上一步甚至是上几步的计算，再通过其它的可能的分步解答再次尝试寻找问题的答案。
 * 回溯法通常用最简单的递归方法来实现，在反复重复上述的步骤后可能出现两种情况：
 * 1. 找到一个可能存在的正确的答案；
 * 2. 在尝试了所有可能的分步方法后宣告该问题没有答案。
 * <p>
 * 解题思路：
 * 我们尝试在纸上写 3 个数字、4 个数字、5 个数字的全排列，相信不难找到这样的方法。以数组 [1, 2, 3] 的全排列为例。
 * 1. 先写以 1 开头的全排列，它们是：[1, 2, 3], [1, 3, 2]，即 1 + [2, 3] 的全排列（注意：递归结构体现在这里。）；
 * 2. 再写以 2 开头的全排列，它们是：[2, 1, 3], [2, 3, 1]，即 2 + [1, 3] 的全排列；
 * 3. 最后写以 3 开头的全排列，它们是：[3, 1, 2], [3, 2, 1]，即 3 + [1, 2] 的全排列。
 * 总结搜索的方法：按顺序枚举每一位可能出现的情况，已经选择的数字在 当前 要选择的数字中不能出现。按照这种策略搜索就能够做到 不重不漏。
 * 这样的思路，可以用一个树形结构表示。递归的终止条件是：一个排列中的数字已经选够。
 *
 * 时间复杂度：O(n×n!)
 *
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/15下午9:08
 */
class Permutations_46 {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 阶段变量 path 是一个栈
        Stack<Integer> path = new Stack<>();
        backtrack(nums, path);
        return result;
    }

    private void backtrack(int[] nums, Stack<Integer> path) {
        if (nums.length <= 0) {
            // 递归的终止条件是： 一个排列中的数字已经选够
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 下一阶段剩下得数组： [1, 2, 3] -> 2 + [1, 3]
            int[] temp = new int[nums.length - 1];
            // 拷贝数组
            System.arraycopy(nums, 0, temp, 0, i);
            System.arraycopy(nums, i + 1, temp, i, nums.length - i - 1);
            // 递进
            path.push(nums[i]);
            backtrack(temp, path);
            // 回溯
            path.pop();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Permutations_46 solution = new Permutations_46();
        List<List<Integer>> result = solution.permute(nums);
        System.out.println(result);
    }

}
