package com.junbin.algorithm.first.twenty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
 * 思路：和上一道题类似，也用队列来遍历，但这里不需要双端队列了。直接看代码注释就很清楚了！
 *
 * @author junbin.wang
 * @date 2023/2/14下午9:10
 */
public class LevelOrderTree {
    public List<List<Integer>> levelOrder(ZigzagLevelOrder.TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }
        Queue<ZigzagLevelOrder.TreeNode> queue = new LinkedList<ZigzagLevelOrder.TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 用以存放当前这一层的所有节点值
            List<Integer> level = new ArrayList<Integer>();
            for (int i = 1; i <= queue.size(); ++i) {
                ZigzagLevelOrder.TreeNode node = queue.poll();
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
