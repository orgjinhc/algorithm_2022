package leetcode.daily.medium;

import base.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Daily_114_Flatten {

    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        List<TreeNode> ans = new ArrayList<>();
        preProcess(root, ans);

        TreeNode tmp = root;
        for (int i = 1; i < ans.size(); i++) {
            tmp.left = null;
            tmp.right = ans.get(i);
            tmp = tmp.right;
        }
    }

    public static void preProcess(TreeNode node, List<TreeNode> ans) {
        if (node == null) {
            return;
        }
        ans.add(node);
        preProcess(node.left, ans);
        preProcess(node.right, ans);
    }


    public static void flattenByLinkedList(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode head = process(root).head;
    }

    public static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        //  ... 5 ...
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        //        5
        //  2...4   9...13

        if (leftInfo != null) {
            leftInfo.tail.right = node;
            node.left = leftInfo.tail;
        }

        if (rightInfo != null) {
            rightInfo.head.left = node;
            node.right = rightInfo.head;
        }

        TreeNode head = leftInfo != null ? leftInfo.head : node;
        TreeNode tail = rightInfo != null ? rightInfo.tail : node;
        return new Info(head, tail);
    }

    public static class Info {
        TreeNode head;
        TreeNode tail;

        public Info(TreeNode h, TreeNode t) {
            head = h;
            tail = t;
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        node.setLeft(node1);
        node.setRight(node2);

        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);

        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setRight(node5);
        flattenByLinkedList(node);
        flatten(node);
        pre(node);

    }


    public static void pre(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        pre(node.left);
        pre(node.right);
    }
}
