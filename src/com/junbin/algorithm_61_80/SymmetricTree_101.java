package com.junbin.algorithm_61_80;

import com.junbin.model.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. 对称二叉树 -简单
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * 思路：
 * 递归：如果一个树的左子树与右子树镜像对称，那么这个树是对称的。因此，该问题可以转化为：两个树在什么情况下互为镜像？
 * 如果同时满足下面的条件，两个树互为镜像：
 * 1. 它们的两个根结点具有相同的值
 * 2. 每个树的右子树都与另一个树的左子树镜像对称
 * 我们可以实现这样一个递归函数，通过「同步移动」两个指针的方法来遍历这棵树，p 指针和 q 指针一开始都指向这棵树的根，
 * 随后 p 右移时，q 左移，p 左移时，q 右移。每次检查当前 p 和 q 节点的值是否相等，如果相等再判断左右子树是否对称。
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 * <p>
 * 迭代: 首先我们引入一个队列，这是把递归程序改写成迭代程序的常用方法。初始化时我们把根节点入队两次。
 * 每次提取两个结点并比较它们的值（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像），
 * 然后将两个结点的左右子结点按相反的顺序插入队列中。当队列为空时，或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，
 * 该算法结束。
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author junbin.wang
 * @date 2023/2/22上午8:25
 */
public class SymmetricTree_101 {
    public static class DiGui {
        public boolean isSymmetric(TreeNode root) {
            return check(root, root);
        }

        public boolean check(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
        }
    }

    public static class DieDai {
        public boolean isSymmetric(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                return true;
            }
            //用队列保存节点
            LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
            //将根节点的左右孩子放到队列中
            queue.add(root.left);
            queue.add(root.right);
            while (queue.size() > 0) {
                //从队列中取出两个节点，再比较这两个节点
                TreeNode left = queue.removeFirst();
                TreeNode right = queue.removeFirst();
                //如果两个节点都为空就继续循环，两者有一个为空就返回false
                if (left == null && right == null) {
                    continue;
                }
                if (left == null || right == null) {
                    return false;
                }
                if (left.val != right.val) {
                    return false;
                }
                //将左节点的左孩子， 右节点的右孩子放入队列，因为要比较镜像，所以最左边和最右边的一起比，中间的一起比
                queue.add(left.left);
                queue.add(right.right);
                //将左节点的右孩子，右节点的左孩子放入队列
                queue.add(left.right);
                queue.add(right.left);
            }

            return true;
        }
    }
}
