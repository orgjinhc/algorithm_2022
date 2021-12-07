package base.tree.recursive;

import base.tree.TreeNode;

public class PathSum {

    public static void main(String[] args) {

    }
    public static boolean isSum = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(null == root) return false;
        isSum = false;
        process(root,0,targetSum);
        return isSum;
    }

    /**
     * 递归实现
     *
     * @param x
     * @param preSum
     * @param targetSum
     */
    public static void process(TreeNode x, int preSum, int targetSum) {

        //  设计BaseCase
        //  叶子结点方案
        if (x.left == null && x.right == null) {
            if (x.val + preSum == targetSum) {
                isSum = true;
                return;
            }
        }

        //  非叶子节点, 累加当前节点继续往下传preSum
        preSum += x.val;

        if (x.left != null) {
            process(x.left, preSum, targetSum);
        }


        if (x.right != null) {
            process(x.right, preSum, targetSum);
        }
    }
}
