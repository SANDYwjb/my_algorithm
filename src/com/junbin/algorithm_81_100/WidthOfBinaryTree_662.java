package com.junbin.algorithm_81_100;

import com.junbin.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 662. 二叉树最大宽度-中等
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。树的 最大宽度 是所有层中最大的 宽度 。
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，
 * 两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * 输入：root = [1,3,2,5,3,null,9]
 * 输出：4
 * 解释：最大宽度出现在树的第 3 层，宽度为 4 (5,3,null,9) 。
 * 输入：root = [1,3,2,5,null,null,9,6,null,7]
 * 输出：7
 * 解释：最大宽度出现在树的第 4 层，宽度为 7 (6,null,null,null,null,null,7) 。
 * <p>
 * 思路：深度优先搜索
 * 根据满二叉树的节点编号规则：若根节点编号为index，则其左子节点编号为 index*2，其右节点编号为index*2+1。
 * 一个朴素的想法是：我们在 DFS过程中使用两个哈希表分别记录每层深度中的最小节点编号和最大节点编号，两者距离即是当前层的宽度，
 * 最终所有层数中的最大宽度即是答案。而实现上，我们可以利用先 DFS 左节点，再 DFS 右节点的性质可知，每层的最左节点必然是最先被遍历到，
 * 因此我们只需要记录当前层最先被遍历到点编号（即当前层最小节点编号），并在 DFS 过程中计算宽度，更新答案即可。
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author junbin.wang
 * @date 2023/2/25下午8:00
 */
public class WidthOfBinaryTree_662 {
    Map<Integer, Integer> map = new HashMap<>();
    int ans;

    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 1, 0);
        return ans;
    }

    void dfs(TreeNode root, int index, int depth) {
        if (root == null) {
            return;
        }
        if (!map.containsKey(depth)) {
            map.put(depth, index);
        }
        ans = Math.max(ans, index - map.get(depth) + 1);
        index = index - map.get(depth) + 1;
        dfs(root.left, index * 2, depth + 1);
        dfs(root.right, index * 2 + 1, depth + 1);
    }
}
