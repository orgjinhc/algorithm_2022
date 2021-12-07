package base.tree.recursive.routine;

import base.tree.TreeNode;

/**
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，返回整课二叉树的最大距离
 */
public class MaxDistance {


    /**
     * 需要返回的信息
     */
    static class Info {
        //  树高
        int height;
        //  最大路径：左树高度 + 1 + 右树高度
        int maxDistance;

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

    }
}
