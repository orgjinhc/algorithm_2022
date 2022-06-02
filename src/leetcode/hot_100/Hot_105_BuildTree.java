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
     * @param preOrderLeft
     * @param preOrderRight
     * @param in
     * @param inOrderLeft
     * @param inOrderRight
     * @return
     */
    public static TreeNode build(int[] pre, int preOrderLeft, int preOrderRight, int[] in, int inOrderLeft, int inOrderRight) {
        if (preOrderLeft > preOrderRight) {
            return null;
        }
        /**
         * 前序遍历顺序: 头 -> 左 -> 右
         * 整棵树的头节点一定是 preOrderLeft, 先构建出来
         */
        TreeNode head = new TreeNode(pre[preOrderLeft]);
        /**
         * 只有一个节点情况
         */
        if (preOrderLeft == preOrderRight) {
            return head;
        }

        /**
         * inHeadIndex是中序的头节点, 中序遍历的顺序决定了 左右两课子树的边界
         * 找到中序的头节点的位置, 中序的头等于先序的头
         */
        int inHeadIndex = inOrderLeft;
        while (in[inHeadIndex] != pre[preOrderLeft]) {
            inHeadIndex++;
        }

        /**
         * 确定了头和左右树的边界, 递归构建即可
         * 前序无法确定左、右数的边界, 通过中序的左、右数来确定范围
         * 前序数组[左树的范围] = preOrderLeft + 1, preOrderLeft + (中序数组[左数范围] = inHeadIndex - inOrderLeft)
         */
        head.left = build(pre, preOrderLeft + 1, preOrderLeft + (inHeadIndex - inOrderLeft), in, inOrderLeft, inHeadIndex);
        head.right = build(pre, preOrderLeft + (inHeadIndex - inOrderLeft) + 1, preOrderRight, in, inHeadIndex + 1, inOrderRight);
        return head;
    }

}
