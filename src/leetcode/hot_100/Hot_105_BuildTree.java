package leetcode.hot_100;

import base.tree.TreeNode;

/**
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class Hot_105_BuildTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (null == preorder || null == inorder || preorder.length != inorder.length) {
            return null;
        }
        return f(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public static TreeNode f(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        //  核心逻辑, 先[1,2,3] 中[2,3,1]
        //  核心逻辑, 先[1,2,3] 中[1,2,3], L1 + find - L2 == L1
        if (L1 > R1) {
            return null;
        }

        TreeNode head = new TreeNode(pre[L1]);
        if (L1 == R1) {
            return head;
        }

        //  find的中序的头节点
        int find = L2;
        //  找到中序的头节点的位置, 中序的头等于先序的头
        while (in[find] != pre[L1]) {
            find++;
        }

        head.left = f(pre, L1 + 1, L1 + find - L2, in, L2, find);
        head.right = f(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);
        return head;
    }

}
