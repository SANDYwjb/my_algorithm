package com.junbin.algorithm_41_60;

import com.junbin.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历-简单
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * 思路：递归
 * 首先我们需要了解什么是二叉树的前序遍历：按照访问根节点——左子树——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候，我们按照同样的方式遍历，
 * 直到遍历完整棵树。因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程。
 * <p>
 * 定义 preorder(root) 表示当前遍历到 root 节点的答案。按照定义，我们只要首先将 root 节点的值加入答案，
 * 然后递归调用 preorder(root.left) 来遍历 root 节点的左子树，最后递归调用 preorder(root.right) 来遍历 root 节点的右子树即可，
 * 递归终止的条件为碰到空节点。
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author junbin.wang
 * @date 2023/2/19下午3:22
 */
public class BinaryTreePreorderTraversal_144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }
}
