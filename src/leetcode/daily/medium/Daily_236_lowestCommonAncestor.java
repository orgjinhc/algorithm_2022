package leetcode.daily.medium;

import base.tree.TreeNode;

import java.util.HashMap;
import java.util.HashSet;

public class Daily_236_lowestCommonAncestor {

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


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

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
        Node parentOfCurNode = parentMap.get(cur);
        while (parentOfCurNode != null) {
            cur = parentOfCurNode;
            pParentSet.add(cur);
        }
        cur = q;
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

}
