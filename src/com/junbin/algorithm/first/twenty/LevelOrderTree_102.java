package com.junbin.algorithm.first.twenty;

import com.junbin.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树层序遍历 -中等
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * 思路：和上一道题类似，也用队列来遍历，但这里不需要双端队列了。直接看代码注释就很清楚了！
 *
 * @author junbin.wang
 * @date 2023/2/14下午9:10
 */
public class LevelOrderTree_102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 用以存放当前这一层的所有节点值
            List<Integer> level = new ArrayList<Integer>();
            for (int i = 1; i <= queue.size(); ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }
}
