package com.junbin.algorithm_61_80;

import com.junbin.model.TreeNode;

/**
 * 543. 二叉树的直径 -简单
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。比如一条路径1->2->3->4,则长度是3.
 * <p>
 * 思路：深度优先搜索
 * 首先我们知道一条路径的长度为该路径经过的节点数减一，所以求直径（即求路径长度的最大值）等效于求路径经过节点数的最大值减一。
 * 而任意一条路径均可以被看作由某个节点为起点，从其左儿子和右儿子向下遍历的路径拼接得到。
 * 时间复杂度：O(n)
 * 空间复杂度：O(height)
 * <p>
 * 看不懂得看图解才行，很简单的。
 * https://leetcode.cn/problems/diameter-of-binary-tree/solutions/139683/er-cha-shu-de-zhi-jing-by-leetcode-solution/
 *
 * @author junbin.wang
 * @date 2023/2/22上午8:37
 */
public class DiameterOfBinaryTree_543 {
    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L + R + 1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }
}
