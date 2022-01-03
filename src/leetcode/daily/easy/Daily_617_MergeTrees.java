package leetcode.daily.easy;

import base.tree.TreeNode;

public class Daily_617_MergeTrees {

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        merge(root1, root2);
        return root1;
    }

    public static TreeNode dfs(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }

        if (root2 == null) {
            return root1;
        }

        TreeNode head = new TreeNode(root1.val + root2.val);
        head.left = dfs(root1.left, root2.left);
        head.right = dfs(root1.right, root2.right);
        return head;
    }


    public static void merge(TreeNode root1, TreeNode root2) {
        if ((root1 == null && root2 == null) || root2 == null) {
            return;
        }
        if (root1 != null && root2 != null) {
            root1.val = root1.val + root2.val;
        }
        if (root1.left == null) {
            root1.left = root2.left;
            return;
        }
        merge(root1.left, root2.left);
        if (root1.right == null) {
            root1.right = root2.right;
            return;
        }
        merge(root1.right, root2.right);
    }

    public static void preProcess(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        preProcess(node.left);
        preProcess(node.right);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        node.setLeft(node1);
        node.setRight(node2);

        TreeNode node3 = new TreeNode(5);

        node1.setLeft(node3);


        TreeNode root = new TreeNode(2);
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(3);
        root.setLeft(root1);
        root.setRight(root2);

        TreeNode root3 = new TreeNode(4);
        TreeNode root4 = new TreeNode(7);

        root1.setRight(root3);
        root2.setRight(root4);

        preProcess(mergeTrees(node, root));
    }
}