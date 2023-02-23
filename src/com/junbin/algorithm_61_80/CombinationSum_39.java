package com.junbin.algorithm_61_80;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和-中等
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，
 * 并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * <p>
 * 思路：搜索回溯
 * 对于这类寻找所有可行解的题，我们都可以尝试用「搜索回溯」的方法来解决。
 * 回到本题，我们定义递归函数 dfs(target,combine,idx)表示当前在 candidates数组的第 idx位，还剩 target要组合，已经组合的列表为 combine。
 * 递归的终止条件为 target≤0或者 candidates 数组被全部用完。那么在当前的函数中，每次我们可以选择跳过不用第 idx个数，
 * 即执行 dfs(target,combine,idx+1)。也可以选择使用第 idx个数，即执行 dfs(target−candidates[idx],combine,idx)，注意到每个数字可以被无限制重复选取，
 * 因此搜索的下标仍为 idx。
 * 当然，搜索回溯的过程一定存在一些优秀的剪枝方法来使得程序运行得更快，而这里只给出了最朴素不含剪枝的写法。
 * 包含剪枝的可以看这篇：https://leetcode.cn/problems/combination-sum/solutions/14697/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
 *
 * @author junbin.wang
 * @date 2023/2/23上午8:33
 */
public class CombinationSum_39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> combine = new ArrayList<Integer>();
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 直接跳过
        dfs(candidates, target, ans, combine, idx + 1);
        // 选择当前数
        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1);
        }
    }
}
