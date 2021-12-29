package leetcode.top_200.medium;

import base.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第k个最小元素（从 1 开始计数）。
 * 示例 1：
 * <p>
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * <p>
 * 树中的节点数为 n
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 */
public class Top_230_KthSmallest {

    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> ans = new ArrayList<>();
        midProcess(root, ans);
        return ans.get(k);
    }

    public static void midProcess(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        midProcess(node.left, ans);
        ans.add(node.val);
        midProcess(node.right, ans);
    }

    public static void main(String[] args) {

    }
}
