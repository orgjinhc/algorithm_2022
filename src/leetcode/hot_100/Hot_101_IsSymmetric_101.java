package leetcode.hot_100;

import base.tree.TreeNode;
import com.sun.jmx.remote.internal.ArrayQueue;
import leetcode.daily.medium.LCP_3_robot;
import leetcode.util.LCUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二 叉树，检查它是否是镜像对称的。
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
public class Hot_101_IsSymmetric_101 {

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

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.push(root.left);
        queue.push(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.pop();
            TreeNode right = queue.pop();

            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null) {
                return false;
            }

            if (left.val != right.val) {
                return false;
            }

            queue.push(left.left);
            queue.push(right.right);
            queue.push(left.right);
            queue.push(right.left);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        TreeNode leftLeft = new TreeNode(2);
        TreeNode rightLeft = new TreeNode(2);
        root.left = left;
        root.right = right;
        left.left = leftLeft;
        right.left = rightLeft;
        System.out.println(isSymmetric1(root));
        System.out.println(isSymmetric2(root));
    }
}
