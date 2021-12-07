package base.tree.recursive;

import base.tree.TreeNode;

public class BinaryBalancedTree {

    public static void main(String[] args) {

    }

    public static boolean isBalance(TreeNode root) {
        return process(root).isBalance;
    }

    /**
     * 递归逻辑
     * - 先处理左树
     * - 再处理右树
     *
     * @param node
     * @return
     */
    public static Info process(TreeNode node) {
        if (null == node) {
            return new Info(0, true);
        }

        //  当前节点的左孩子和右孩子均已处理完毕, 计算当前节点高度
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        //  计算当前节点的高度
        int heightTree = Math.max(leftInfo.heightTree, rightInfo.heightTree) + 1;

        //  判断当前节点是否是 balance
        boolean isBalance = leftInfo.isBalance && rightInfo.isBalance && (Math.abs(leftInfo.heightTree - rightInfo.heightTree) < 2);

        return new Info(heightTree, isBalance);
    }


    static class Info {
        boolean isBalance;

        int heightTree;

        Info(int heightTree, boolean isBalance) {
            this.heightTree = heightTree;
            this.isBalance = isBalance;
        }
    }
}
