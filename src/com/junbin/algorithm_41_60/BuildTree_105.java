package com.junbin.algorithm_41_60;

import com.junbin.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树-中等
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * 思路：
 * 前序遍历：先遍历根节点；随后递归地遍历左子树；最后递归地遍历右子树。
 * 中序遍历：先递归地遍历左子树；随后遍历根节点；最后递归地遍历右子树。
 * 思路：递归
 * 对于任意一颗树而言，前序遍历的形式总是：[ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ] 即根节点总是前序遍历中的第一个节点。
 * 而中序遍历的形式总是：[ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
 * 只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，
 * 因此我们就可以对应到前序遍历的结果中，对上述形式中的所有左右括号进行定位。这样一来，我们就知道了左子树的前序遍历和中序遍历结果，
 * 以及右子树的前序遍历和中序遍历结果，我们就可以递归地对构造出左子树和右子树，再将这两颗子树接到根节点的左右位置。
 * 具体来说：
 * 在中序遍历中对根节点进行定位时，一种简单的方法是直接扫描整个中序遍历的结果并找出根节点，但这样做的时间复杂度较高。
 * 我们可以考虑使用哈希表来帮助我们快速地定位根节点。对于哈希映射中的每个键值对，键表示一个元素（节点的值），值表示其在中序遍历中的出现位置。
 * 在构造二叉树的过程之前，我们可以对中序遍历的列表进行一遍扫描，就可以构造出这个哈希映射。在此后构造二叉树的过程中，
 * 我们就只需要 O(1)的时间对根节点进行定位了。
 * <p>
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author junbin.wang
 * @date 2023/2/19上午10:46
 */
public class BuildTree_105 {
    private Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right,
                                int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        // 因为中序遍历的根节点左边，就是它的左子树 的中序遍历结果
        root.left = myBuildTree(preorder, inorder, preorder_left + 1,
                preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        // 因为中序遍历的根节点右边，就是它的右子树 的中序遍历结果
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right,
                inorder_root + 1, inorder_right);
        return root;
    }
}
