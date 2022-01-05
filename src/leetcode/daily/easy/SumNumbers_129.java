package leetcode.daily.easy;

import leetcode.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 */
public class SumNumbers_129 {


    public static int sumNumbers(TreeNode root) {
        List<String> pathNumbers = new ArrayList<>();
        process(root, "", pathNumbers);
        int ans = 0;
        for (String an : pathNumbers) {
            ans += Integer.valueOf(an);
        }
        return ans;
    }

    public static void process(TreeNode node, String path, List<String> ans) {
        if (node.left == null && node.right == null) {
            ans.add(path + node.val);
            return;
        }

        if (node.left != null) {
            process(node.left, path + node.val, ans);
        }

        if (node.right != null) {
            process(node.right, path + node.val, ans);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(1);
//        TreeNode right = new TreeNode(0);
//        TreeNode leftLeft = new TreeNode(5);
//        TreeNode leftRight = new TreeNode(1);
        root.left = left;
//        root.right = right;
//        left.left = leftLeft;
//        left.right = leftRight;
        System.out.println(sumNumbers(root));

    }
}
