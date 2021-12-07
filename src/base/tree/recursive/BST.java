package base.tree.recursive;

import base.tree.TreeNode;

public class BST {

    public static void main(String[] args) {

    }

    public static boolean bst(TreeNode root) {
        return BinaryBalancedTree.process(root).isBalance && BinarySearchTree.process(root).isBST;
    }
}
