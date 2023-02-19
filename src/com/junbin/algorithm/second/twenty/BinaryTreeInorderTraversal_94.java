package com.junbin.algorithm.second.twenty;

import com.junbin.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** 94. 二叉树的中序遍历-简单
 * 给定一个二叉树的根节点root，返回它的中序遍历。按照访问左子树——根节点——右子树的方式遍历
 *
 * 递归
 * 按照访问左子树——根节点——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树。
 * 因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程。
 *
 * @author junbin.wang
 * @date 2023/2/15下午9:28
 */
public class BinaryTreeInorderTraversal_94 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<Integer>();
            inorder(root, res);
            return res;
        }

        public void inorder(TreeNode root, List<Integer> res) {
            if (root == null) {
                return;
            }
            inorder(root.left, res);
            res.add(root.val);
            inorder(root.right, res);
        }
}
