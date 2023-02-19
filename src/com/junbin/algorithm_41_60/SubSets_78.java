package com.junbin.algorithm_41_60;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集-中等
 * 给你一个整数数组 nums ，数组中的元素互不相同。返回该数组所有可能的子集（幂集）。解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 思路：动态规划
 * dp[i]表示前i个数的解集，dp[i] = dp[i - 1] + collections(i)。其中，collections(i)表示把dp[i-1]的所有子集都加上第i个数形成的子集。
 * 【具体操作】
 * 因为nums大小不为0，故解集中一定有空集。令解集一开始只有空集，然后遍历nums，每遍历一个数字，拷贝解集中的所有子集，
 * 将该数字与这些拷贝组成新的子集再放入解集中即可。时间复杂度为O(n^2)。
 * 例如[1,2,3]，一开始解集为[[]]，表示只有一个空集。
 * 遍历到1时，依次拷贝解集中所有子集，只有[]，把1加入拷贝的子集中得到[1]，然后加回解集中。此时解集为[[], [1]]。
 * 遍历到2时，依次拷贝解集中所有子集，有[], [1]，把2加入拷贝的子集得到[2], [1, 2]，然后加回解集中。此时解集为[[], [1], [2], [1, 2]]。
 * 遍历到3时，依次拷贝解集中所有子集，有[], [1], [2], [1, 2]，把3加入拷贝的子集得到[3], [1, 3], [2, 3], [1, 2, 3]，然后加回解集中。
 * 此时解集为[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]。
 *
 * @author junbin.wang
 * @date 2023/2/19下午3:25
 */
public class SubSets_78 {
    public List<List<Integer>> subsets(int[] nums) {
        // 解集
        List<List<Integer>> lists = new ArrayList<>();
        // 首先将空集加入解集中
        lists.add(new ArrayList<Integer>());
        // 当前子集数
        for (int i = 0; i < nums.length; i++) {
            int size = lists.size();
            // 拷贝所有子集
            for (int j = 0; j < size; j++) {
                List<Integer> newList = new ArrayList<>(lists.get(j));
                // 向拷贝的子集中加入当前数形成新的子集
                newList.add(nums[i]);
                // 向lists中加入新子集
                lists.add(newList);
            }
        }
        return lists;
    }
}
