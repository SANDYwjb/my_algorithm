package com.junbin.algorithm.first;

/** 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 示例 1：
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *
 * 输出：3
 *
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 *
 * 示例 2：
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 *
 * 输出：5
 *
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 示例 3：
 *
 * 输入：root = [1,2], p = 1, q = 2
 *
 * 输出：1
 *
 * 解法描述的实在有点复杂，直接看原文链接吧。这道题虽然原理比较复杂，但是代码实现很简单，考的概率比较大。https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/14下午9:16
 */
public class LowestCommonAncestor {
    class Solution {
        public ZigzagLevelOrder.TreeNode lowestCommonAncestor(ZigzagLevelOrder.TreeNode root, ZigzagLevelOrder.TreeNode p, ZigzagLevelOrder.TreeNode q) {
            if(root == null || root == p || root == q) return root; // 这里因为p或者q只要有一个是根节点，那不用说，祖先肯定就是root了。这里的root节点不是整棵树的根节点，在递归里是当前子树的根节点。道理是一样的。
            ZigzagLevelOrder.TreeNode left = lowestCommonAncestor(root.left, p, q);
            ZigzagLevelOrder.TreeNode right = lowestCommonAncestor(root.right, p, q);
            if(left == null) return right;
            if(right == null) return left;
            return root;
        }
    }
}
