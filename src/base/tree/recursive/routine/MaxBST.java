package base.tree.recursive.routine;

import base.tree.TreeNode;

/**
 *
 * 给定一颗树的头结点head
 * 返回这棵二叉树中最大的二叉搜索子树的头结点
 */
public class MaxBST {

    static class Info {
        int max;
        int min;
        boolean isBalance;
        int maxSubBSTSize;

        public Info(int max, int min, boolean isBalance, int maxSubBSTSize) {
            this.max = max;
            this.min = min;
            this.isBalance = isBalance;
            this.maxSubBSTSize = maxSubBSTSize;
        }
    }


    public static Info process(TreeNode X) {

        if (X == null) {
            return null;
        }
        Info left = process(X.left);
        Info right = process(X.right);

        int maxSubBSTSize = 0;
        boolean isBalance = false;
        int max = X.val;
        int min = X.val;

        //  最大值、最小值、二叉搜索树节点数
        if (null != left) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            if (left.isBalance) {
                maxSubBSTSize = left.maxSubBSTSize;
            }
        }

        //  最大值、最小值、二叉搜索树节点数
        if (null != right) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            if (right.isBalance) {
                maxSubBSTSize = Math.max(maxSubBSTSize, right.maxSubBSTSize);
            }
        }

        //  当前节点为头节点的树, 是不是二叉搜索树
        if ((left == null ? true : left.isBalance) && (right == null ? true : right.isBalance) && (left == null ? true : left.max < X.val) && (right == null ? true : right.max > X.val)) {
            isBalance = true;
            int leftMaxSubBSTSize = left == null ? 0 : left.maxSubBSTSize;
            int rightMaxSubBSTSize = right == null ? 0 : right.maxSubBSTSize;
            maxSubBSTSize = leftMaxSubBSTSize + 1 + rightMaxSubBSTSize;
        }
        return new Info(max, min, isBalance, maxSubBSTSize);
    }


    public static void main(String[] args) {

    }

}
