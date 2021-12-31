package leetcode.daily.easy;

import base.tree.TreeNode;

public class Daily_111_MinDepth {

    public int minDepth(TreeNode root) {
        return midProcess(root, 0);
    }

    public static int midProcess(TreeNode node, int ans) {
        if (node.left == null && node.right == null) {
            return ans + 1;
        }
        int leftDepth = Integer.MIN_VALUE;
        int rightDepth = Integer.MIN_VALUE;
        if (node.left == null) {
            leftDepth = midProcess(node.right, ans + 1);
        }

        if (node.right == null) {
            rightDepth = midProcess(node.left, ans + 1);
        }
        return Math.max(leftDepth, rightDepth);
    }
}
