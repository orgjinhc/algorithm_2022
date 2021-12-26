package leetcode.hot_100;

import leetcode.util.TreeNode;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * <p>
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 */
public class MaxPathSum_124 {

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxPathSum;
    }

    /**
     * 每个node需要向上返回的信息
     */
    public static class Info {

        /**
         * 不包含头的最大路径和
         */
        int maxPathSum;

        /**
         * 包含头的最大路径和
         */
        int maxPathSumFromHead;

        public Info(int maxPathSum, int maxPathSumFromHead) {
            this.maxPathSum = maxPathSum;
            this.maxPathSumFromHead = maxPathSumFromHead;
        }
    }

    /**
     * 递归行为, 获取info信息
     * 核心思路
     * 1.与当前节点有关 -> maxPathSumFromHead(有无左右两边的情况, 每次都要和左、右进行相加与当前自身值做比较)
     * 2.与当前节点无关 -> maxPathSum(有无左右两边的情况, 每次无需把自己和左右两边进行相加. 特殊情况:与当前节点有关的max 大于 左右两边的max, 当前变量更新)
     * 3.有关无关两种情况求最大 -> 上面考虑的都是单边情况, 如果左右两边包含头的最大路径和都大于0, 用得到的max与三数和进行比较求max
     *
     * @param node
     * @return
     */
    public static Info process(TreeNode node) {
        if (node == null) {
            return null;
        }

        Info leftProcess = process(node.left);
        Info rightProcess = process(node.right);
        //  与当前节点有关的逻辑部分

        //  当前节点是叶子节点情况, 包含头的最大路径和为当前节点自身
        int maxPathSumFromHead = node.val;

        //  当前节点非叶子节点, 有左树情况
        if (leftProcess != null) {
            //  当前节点包含头的最大路径和为当前节点+左树的包含头的最大路径和与自己比较
            maxPathSumFromHead = Math.max(leftProcess.maxPathSumFromHead + node.val, maxPathSumFromHead);
        }

        //  当前节点非叶子节点, 有右树情况
        if (rightProcess != null) {
            maxPathSumFromHead = Math.max(rightProcess.maxPathSumFromHead + node.val, maxPathSumFromHead);
        }

        //  截止目前获取的包含头的最大路径和 可能是节点本身、也可能是左+节点、也可能是右+节点, 无论如何maxPathSumFromHead一定需要包含当前节点


        //  与当前节点无关的逻辑部分

        //  下面再来处理不包含头的最大路径和
        //  当前节点是叶子节点情况下, 最大路径和就是自身
        int maxPathSum = node.val;

        //  左树不为空, 获取左树的最大路径和与当前位置的最大路径和比较
        if (leftProcess != null) {
            maxPathSum = Math.max(maxPathSum, leftProcess.maxPathSum);
        }
        //  右树不为空, 获取右树的最大路径和与当前位置的最大路径和比较
        if (rightProcess != null) {
            maxPathSum = Math.max(maxPathSum, rightProcess.maxPathSum);
        }
        //  有关无关结合取最大部分

        //  还有一种可能:包含头的最大路径和比获取到的最大路径和要大的情况
        maxPathSum = Math.max(maxPathSum, maxPathSumFromHead);

        //  最后一种可能就是:左树+当前节点+右树是最大的路径case(左树包含头的最大路径和大于0并且右树包含头的最大路径和也大于0)
        if (leftProcess != null && rightProcess != null && leftProcess.maxPathSumFromHead > 0 && rightProcess.maxPathSumFromHead > 0) {
            maxPathSum = Math.max(maxPathSum, leftProcess.maxPathSumFromHead + node.val + rightProcess.maxPathSumFromHead);
        }
        return new Info(maxPathSum, maxPathSumFromHead);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
//        TreeNode right = new TreeNode(20);
//        TreeNode rightRight = new TreeNode(7);
//        TreeNode rightLeft = new TreeNode(15);
        root.left = left;
//        root.right = right;
//        right.right = rightRight;
//        right.left = rightLeft;
        System.out.println(maxPathSum(root));
    }
}
