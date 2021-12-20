package leetcode.hot_100;

import base.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 翻转一棵二叉树。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * <p>
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 */
public class InvertTree_226 {

    /**
     * 递归实现
     *
     * @param root
     * @return
     */
    public static TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree1(root.left);
        TreeNode right = invertTree1(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        List<TreeNode> ans = new ArrayList<>();
        //  中序遍历
        midProcess(root, ans);
        //  反转数组
        invertArr(ans);
        //  反序列化树
        return serialize(ans);
    }

    public static void midProcess(TreeNode node, List<TreeNode> ans) {
        if (node == null) {
            return;
        }
        midProcess(node.left, ans);
        ans.add(node);
        midProcess(node.right, ans);
    }

    public static void invertArr(List<TreeNode> ans) {
        int L = 0;
        int R = ans.size() - 1;
        while (L <= R) {
            swap(ans, L++, R--);
        }
    }

    public static void swap(List<TreeNode> ans, int L, int R) {
        TreeNode tmp = ans.get(L);
        ans.set(L, ans.get(R));
        ans.set(R, tmp);
    }

    public static TreeNode serialize(List<TreeNode> ans) {
        return midSerialize(ans, 0, ans.size() - 1);
    }

    public static TreeNode midSerialize(List<TreeNode> ans, int L, int R) {
        if (L > R) {
            return null;
        }
        if (L == R) {
            return ans.get(L);
        }
        //  head位置
        int mid = (L + R) / 2;
        TreeNode head = ans.get(mid);
        head.setLeft(midSerialize(ans, L, mid - 1));
        head.setRight(midSerialize(ans, mid + 1, R));
        return head;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(null);
        TreeNode node2 = new TreeNode(2);
        node.setLeft(node1);
        node.setRight(node2);

//        TreeNode node = new TreeNode(4);
//        TreeNode node1 = new TreeNode(2);
//        TreeNode node2 = new TreeNode(7);
//        node.setLeft(node1);
//        node.setRight(node2);
//
//        TreeNode node3 = new TreeNode(1);
//        TreeNode node4 = new TreeNode(3);
//        TreeNode node5 = new TreeNode(9);
//        TreeNode node6 = new TreeNode(6);
//
//        node1.setLeft(node3);
//        node1.setRight(node4);
//        node2.setLeft(node5);
//        node2.setRight(node6);

        TreeNode ans = invertTree(node);

        List<TreeNode> printList = new ArrayList<>();
        midProcess(ans, printList);
        for (TreeNode treeNode : printList) {
            System.out.println(treeNode.val);
        }
    }
}