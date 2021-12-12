package leetcode.hot_100;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * 进阶：
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 * <p>
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 */
public class IsSymmetric_101 {


    public static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(Integer val) {
            this.val = val;
        }

        TreeNode(Integer val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 递归方式
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric2(TreeNode root) {
        return isMorris(root, root);
    }

    private static boolean isMorris(TreeNode h1, TreeNode h2) {
        if (h1 == null && h2 == null) {
            return true;
        }

        if (h1 == null ^ h2 == null) {
            return false;
        }

        return h1.val == h2.val && isMorris(h1.left, h2.right) && isMorris(h1.right, h2.left);
    }

    /**
     * 迭代方式(偶数个节点一定不是镜像树)
     * 终须遍历结果, 从头节点开始判断是否是回文即可
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric1(TreeNode root) {
        if (null == root) {
            return true;
        }

        List<Integer> ans = new ArrayList<>();
        midIter(root, ans);
        if (ans.size() % 2 == 0) {
            return false;
        }

        int M = (0 + ans.size() - 1) / 2;
        int L = M - 1;
        int R = M + 1;

        for (int i = 0; i < M; i++) {
            if (ans.get(L) != ans.get(R)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 中顺遍历
     *
     * @param root
     * @param ans
     */
    private static void midIter(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }
        midIter(root.left, ans);
        ans.add(root.val);
        midIter(root.right, ans);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        TreeNode leftLeft = new TreeNode(2);
        TreeNode leftRight = new TreeNode(null);
        TreeNode rightLeft = new TreeNode(2);
        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        right.left = rightLeft;
        System.out.println(isSymmetric1(root));
    }
}
