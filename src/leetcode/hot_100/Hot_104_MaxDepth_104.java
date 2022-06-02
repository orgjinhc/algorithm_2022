package leetcode.hot_100;

import base.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明:叶子节点是指没有子节点的节点。
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
 */
public class Hot_104_MaxDepth_104 {

    public static int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(maxDepth1(root.left), maxDepth1(root.right)) + 1;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return process(root, 0);
    }

    public static int process(TreeNode node, int level) {
        if (node == null) {
            return level;
        }
        return Math.max(process(node.left, level + 1), process(node.right, level + 1));
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
        System.out.println(maxDepth1(node));
    }
}
