package leetcode.daily.medium;

import base.tree.TreeNode;

public class Daily_687_longestUnivaluePath {
    public static class Info {
        int maxPath;

        int maxPathFromHead;

        public Info(int maxPath, int maxPathFromHead) {
            this.maxPath = maxPath;
            this.maxPathFromHead = maxPathFromHead;
        }
    }

    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxPath - 1;
    }


    /**
     * 核心方法
     * 两大分类
     * 与x有关 - 包含头的情况
     * 与x无关 - 不包含头的情况
     *
     * @param node
     * @return
     */
    public static Info process(TreeNode node) {
        /**
         * 边界条件, 如果当前节点为空, 返回空
         */
        if (node == null) {
            return null;
        }

        /**
         * 获取左右子孩子的信息
         */
        Info leftProcess = process(node.left);
        Info rightProcess = process(node.right);

        /**
         *  与x有关的最大路径
         */
        int maxPathFromHead = 1;

        /**
         * 左右孩子不为空情况
         */
        if (null != leftProcess && node.val == node.left.val) {

            /**
             * 左孩子 + 当前节点 与 只有当前节点比较
             */
            maxPathFromHead = Math.max(maxPathFromHead, leftProcess.maxPathFromHead + 1);
        }
        if (null != rightProcess && node.val == node.right.val) {

            /**
             * 当前与x有关的情况 与 右孩子 + 当前节点 比较
             */
            maxPathFromHead = Math.max(maxPathFromHead, rightProcess.maxPathFromHead + 1);
        }

        /**
         *  与x无关的最大路径
         *  只有当前节点情况
         */
        int maxPath = 0;
        if (null != leftProcess) {
            /**
             * 左孩子不为空
             */
            maxPath = Math.max(maxPath, leftProcess.maxPath);
        }
        if (null != rightProcess) {
            /**
             * 右孩子不为空
             */
            maxPath = Math.max(maxPath, rightProcess.maxPath);
        }

        /**
         * maxPath的含义是 取 与x有关、与x无关两种case下的最大值
         */
        maxPath = Math.max(maxPath, maxPathFromHead);

        /**
         * 如果左孩子、右孩子都存在路径, 并且和当前节点相同的情况下, 与maxPath进行比较取最大
         */
        if (null != rightProcess && leftProcess != null && node.val == node.left.val && node.val == node.right.val) {
            maxPath = Math.max(maxPath, leftProcess.maxPathFromHead + 1 + rightProcess.maxPathFromHead);
        }
        return new Info(maxPath, maxPathFromHead);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(5);
        TreeNode leftRight = new TreeNode(1);
        TreeNode leftLeft = new TreeNode(1);
        TreeNode rightRight = new TreeNode(5);
        root.left = left;
        root.right = right;
        leftLeft.left = leftLeft;
        leftLeft.right = leftRight;
        right.right = rightRight;
        System.out.println(longestUnivaluePath(root));
    }
}