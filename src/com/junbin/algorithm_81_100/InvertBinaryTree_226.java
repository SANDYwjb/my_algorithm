package com.junbin.algorithm_81_100;

import com.junbin.model.TreeNode;

/**
 * 226. 翻转二叉树- 简单
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。这里的翻转是镜像翻转。
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * 思路：递归
 * 显然，我们从根节点开始，递归地对树进行遍历，并从叶子节点先开始翻转。如果当前遍历到的节点 root 的左右两棵子树都已经翻转，那么我们只需要交换两棵子树的位置，
 * 即可完成以 root为根节点的整棵子树的翻转。
 * 时间复杂度O(n)，空间复杂度O(n)
 *
 * @author junbin.wang
 * @date 2023/2/25上午9:14
 */
public class InvertBinaryTree_226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
