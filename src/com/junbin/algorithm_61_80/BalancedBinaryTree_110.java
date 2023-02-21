package com.junbin.algorithm_61_80;

import com.junbin.model.TreeNode;

/**
 * 110. 平衡二叉树-简单
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 平衡二叉树的定义：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * <p>
 * 思路：自底向上的递归
 * 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。
 * 如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 −1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author junbin.wang
 * @date 2023/2/21上午8:35
 */
public class BalancedBinaryTree_110 {
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            // 返回当前子树的高度
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
