package com.junbin.algorithm.first.twenty;

import com.junbin.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 103.二叉树z字形遍历-中等
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）
 * 我们这里要用一个双端队列去存储某一层的结果，你想一下是不是有那种偶数层我们就从左到右打印，奇数层我们从右到左打印，有两点我们怎么知道当前是奇数层还是偶数层，
 * 所以我们需要一个标志，来标记当前这一层是奇数层还是偶数层。第二点就是如果是偶数层我们怎么处理，之前 说了用双向链表来存储某一层的结果，
 * 是偶数层的话就往队列的末尾插入元素就行(偶数层需要从左到右，这样出来的队列顺序就是正确的)；是奇数层往队列的头部插入元素，就ok.
 *
 * @author junbin.wang
 * @date 2023/2/14下午9:08
 */
public class ZigzagLevelOrder_103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        //根节点入队
        queue.offer(root);
        //用来标记奇偶层
        boolean isEven = true;
        bfs(queue, res, isEven);
        return res;
    }

    public void bfs(LinkedList<TreeNode> queue, List<List<Integer>> res, boolean isEven) {
        while (!queue.isEmpty()) {
            //双端队列
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                // 获取并移除当前链表的第一个元素
                TreeNode node = queue.poll();
                if (isEven) {
                    //偶数层入队列的末尾
                    level.offerLast(node.val);
                } else {
                    //奇数层入队列头部
                    level.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            //变换奇偶
            isEven = !isEven;
            //加入结果集
            res.add(new ArrayList<>(level));
        }
    }
}
