package leetcode.hot_100;

import base.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明:叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 */
public class Hot_104_MaxDepth_104 {

    public static int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth1(root.left);
            int rightHeight = maxDepth1(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        process(root, ans, 0);
        return ans.get(0);
    }

    public static void process(TreeNode node, List<Integer> ans, int level) {
        if (node == null) {
            ans.set(0, Math.max(ans.get(0), level));
            return;
        }
        process(node.left, ans, level + 1);
        process(node.right, ans, level + 1);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        node.setLeft(node1);
        node.setRight(node2);
        TreeNode node3 = new TreeNode(15);
        TreeNode node6 = new TreeNode(7);
        node2.setLeft(node3);
        node2.setRight(node6);

        System.out.println(maxDepth(node));
    }
}
