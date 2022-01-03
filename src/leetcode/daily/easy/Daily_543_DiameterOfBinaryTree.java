package leetcode.daily.easy;

import base.tree.TreeNode;
import base.tree.recursive.routine.MaxDistance;

public class Daily_543_DiameterOfBinaryTree {


    public static int diameterOfBinaryTree(TreeNode root) {
        return process(root).maxDistance - 1;
    }
    public static class Info {
        private int height;

        private int maxDistance;

        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }
    public static Info process(TreeNode X) {
        if (X == null) {
            return new Info(0, 0);
        }

        Info left = process(X.left);
        Info right = process(X.right);

        int height = Math.max(left.height, right.height) + 1;
        //  思维方式发生变化, 左右子树内存在最大路径
        int maxDistance = Math.max(Math.max(left.maxDistance, right.maxDistance), left.height + 1 + right.height);
        return new Info(height, maxDistance);
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        node.setLeft(node1);
        node.setRight(node2);

        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);

        node1.setLeft(node3);
        node1.setRight(node4);

        System.out.println(diameterOfBinaryTree(node));
    }
}