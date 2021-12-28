package leetcode.hot_100;

import base.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Hot_339_Rob_339 {

    public static int rob(TreeNode root) {
        if (null == root) {
            return 0;
        }

        if (root.right == null && root.left == null) {
            return root.val;
        }

        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                sum += curNode.val;
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            list.add(sum);
        }

        int[] dp = new int[list.size()];
        dp[0] = list.get(0);
        dp[1] = Math.max(list.get(0), list.get(1));

        for (int i = 2; i < list.size(); i++) {
            int p1 = list.get(i);
            int p2 = dp[i - 1];
            int p3 = p1 + dp[i - 2];
            dp[i] = Math.max(p1, Math.max(p2, p3));
        }
        return dp[list.size() - 1];
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        TreeNode leftRight = new TreeNode(4);
        root.left = left;
        root.right = right;
        left.right = leftRight;
        rob(root);
    }
}
