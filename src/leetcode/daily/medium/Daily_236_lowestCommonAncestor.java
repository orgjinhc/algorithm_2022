package leetcode.daily.medium;

import base.tree.TreeNode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 公共祖先
 */
public class Daily_236_lowestCommonAncestor {

    /**
     * 官方解法
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }
        return null;
    }

    /**
     * 利用简单数据结构实现
     * 通俗易懂
     *
     * @param head
     * @param p
     * @param q
     * @return
     */
    public static Node lowestAncestor1(Node head, Node p, Node q) {
        if (head == null) {
            return null;
        }
        // 	key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        //	填满夫Map
        fillParentMap(head, parentMap);

        //  记录父节点
        HashSet<Node> pParentSet = new HashSet<>();
        Node cur = p;
        pParentSet.add(cur);

        //  p 所有父节点添加到集合里
        Node parentOfCurNode = parentMap.get(cur);
        while (parentOfCurNode != null) {
            cur = parentOfCurNode;
            pParentSet.add(cur);
        }
        cur = q;
        //  q出现在集合内, 就找到里两个节点的公共祖先
        while (!pParentSet.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node root, HashMap<Node, Node> parentMap) {
        Node leftNode = root.left;
        if (leftNode != null) {
            parentMap.put(leftNode, root);
            fillParentMap(leftNode, parentMap);
        }
        Node rightNode = root.right;
        if (rightNode != null) {
            parentMap.put(rightNode, root);
            fillParentMap(rightNode, parentMap);
        }
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

}