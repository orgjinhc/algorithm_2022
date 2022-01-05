package leetcode.daily.easy;

import java.util.List;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 */
public class MaxDepth {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        if (root.children.isEmpty()) {
            return 1;
        }

        int maxDepth = Integer.MIN_VALUE;
        for (Node child : root.children) {
            maxDepth = Math.max(maxDepth(child), maxDepth);
        }
        return maxDepth + 1;
    }

    public static void main(String[] args) {

    }
}