package com.junbin.algorithm_61_80;

import com.junbin.model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 98. 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 1. 节点的左子树只包含 小于 当前节点的数。
 * 2. 节点的右子树只包含 大于 当前节点的数。
 * 3. 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 思路：中序遍历
 * 对二叉搜索树进行「中序遍历」得到的值构成的序列一定是升序的，这启示我们在中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到
 * 的节点的值即可。如果均大于说明这个序列是升序的，整棵树是二叉搜索树，否则不是，下面的代码我们使用栈来模拟中序遍历的过程。
 * 中序遍历是二叉树的一种遍历方式，它先遍历左子树，再遍历根节点，最后遍历右子树。而我们二叉搜索树保证了左子树的节点的值均小于根节点的值，
 * 根节点的值均小于右子树的值，因此中序遍历以后得到的序列一定是升序序列。
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/22上午8:44
 */
public class ValidateBinarySearchTree_98 {
    long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }
}