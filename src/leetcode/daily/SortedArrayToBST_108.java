package leetcode.daily;

import leetcode.util.TreeNode;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * <p>
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * <p>
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 */
public class SortedArrayToBST_108 {

    public static TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    /**
     * 构建一个搜索二叉树
     * 返回头节点
     *
     * @param nums 源数组
     * @param L    做边界
     * @param R    右边界
     * @return
     */
    public static TreeNode process(int[] nums, int L, int R) {
        //  没有区间可以选, 直接返回null
        if (L > R) {
            return null;
        }

        //  就一个节点了, 随便从L or R直接构建一个头节点返回
        if (L == R) {
            return new TreeNode(nums[L]);
        }

        //  有序数组, 直接找到中间节点作为头节点
        int M = (L + R) / 2;
        TreeNode head = new TreeNode(nums[M]);
        head.left = process(nums, L, M - 1);
        head.right = process(nums, M + 1, R);
        return head;
    }

    private static void midIter(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        midIter(root.left);
        midIter(root.right);
    }

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode treeNode = sortedArrayToBST(nums);
        midIter(treeNode);
    }
}
