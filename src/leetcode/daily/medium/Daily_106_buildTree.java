package leetcode.daily.medium;

import base.tree.TreeNode;

public class Daily_106_buildTree {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public static TreeNode build(int[] inorder, int left1, int right1, int[] postorder, int left2, int right2) {

        if (left1 > right1) {
            return null;
        }
        TreeNode head = new TreeNode(postorder[right2]);
        if (left2 == right2) {
            return head;
        }

        int inHead = left1;
        while (inorder[inHead] != postorder[right2]) {
            inHead++;
        }

        head.left = build(inorder, left1, inHead - 1, postorder, left2 + inHead - left1, right2 - 1);
        head.right = build(inorder, inHead + 1, right1, postorder, left2 + inHead - left1 - 1, left1);
        return head;
    }
}
