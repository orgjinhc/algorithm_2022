package leetcode.hot_100;

import base.tree.TreeNode;

/**
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class Hot_105_BuildTree {


    /**
     * 根据前、中序遍历的结果, 重新构建二叉树
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (null == preorder || null == inorder || preorder.length != inorder.length) {
            return null;
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    /**
     * 根据前序数组从L到R构建树
     * 根据中序数组从L到R构建树
     *
     * @param pre
     * @param L1
     * @param R1
     * @param in
     * @param L2
     * @param R2
     * @return
     */
    public static TreeNode build(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
        //  核心逻辑, 先[1,2,3] 中[2,3,1]
        //  核心逻辑, 先[1,2,3] 中[1,2,3], L1 + find - L2 == L1
        if (L1 > R1) {
            return null;
        }
        /**
         * 前序遍历顺序: 头 -> 左 -> 右
         * 整棵树的头节点一定是 L1, 先构建出来
         */
        TreeNode head = new TreeNode(pre[L1]);
        /**
         * 只有一个节点情况
         */
        if (L1 == R1) {
            return head;
        }

        //  find是中序的头节点, 中序遍历的顺序决定了 左右两课子树的边界
        int find = L2;
        //  找到中序的头节点的位置, 中序的头等于先序的头
        while (in[find] != pre[L1]) {
            find++;
        }

        /**
         * 确定了头和左右树的边界, 递归构建即可
         * 左树的范围 = 前序的 L1 + 1, L1 + find - L2
         */
        head.left = build(pre, L1 + 1, L1 + find - L2, in, L2, find);
        head.right = build(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);
        return head;
    }

}
