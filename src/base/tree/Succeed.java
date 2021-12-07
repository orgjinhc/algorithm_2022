package base.tree;

import com.sun.tools.javac.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class Succeed {

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right, TreeNode parent) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }


        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public TreeNode getParent() {
            return parent;
        }

        public void setParent(TreeNode parent) {
            this.parent = parent;
        }
    }


    /**
     * 数据组织方式:中序遍历结构
     * 当前节点的下一节点
     *
     * @param randomNode
     * @return
     */
    public static TreeNode process(TreeNode randomNode) {
        if (null == randomNode) {
            return null;
        }
        //  当前节点是否存在右子树
        Boolean isRight = isRightExist(randomNode);
        if (isRight) {
            //  逻辑
            return getMostLeftNode(randomNode.right);
        } else {
            //  逻辑
            TreeNode parent = randomNode.parent;
            while (parent != null && parent.right == randomNode) {
                randomNode = parent;
                parent = randomNode.parent;
            }
            return parent;
        }
    }

    private static TreeNode getMostLeftNode(TreeNode node) {

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static Boolean isRightExist(TreeNode randomNode) {
        return randomNode.right != null;
    }


    /**
     * 数据组织方式:中序遍历结构
     * 当前节点的下一节点
     *
     * @param randomNode
     * @return
     */
    public static TreeNode violentSolution(TreeNode randomNode) {

        if (null == randomNode) {
            return null;
        }

        //  找到root节点
        TreeNode rootNode = findRootByRecursive(randomNode);

        //  通过中序遍历构建目标结构
        List<TreeNode> ans = new ArrayList<>();
        ans.add(null);
        in(rootNode, ans);

        for (int i = 0; i < ans.size() - 1; i++) {
            if (ans.get(i) == randomNode) {
                return ans.get(i + 1);
            }
        }
        return null;
    }

    private static TreeNode findRoot(TreeNode randomNode) {
        while (randomNode.parent != null) {
            randomNode = randomNode.parent;
        }
        return randomNode;
    }

    private static TreeNode findRootByRecursive(TreeNode randomNode) {
        if (randomNode.parent == null) {
            return randomNode;
        }
        return findRootByRecursive(randomNode.parent);
    }


    public static void in(TreeNode node, List<TreeNode> ans) {
        if (node == null) {
            return;
        }

        in(node.left, ans);
        ans.add(node);
        in(node.right, ans);
    }


    /**
     * 数据组织方式:中序遍历结构
     * 当前节点的下一节点
     *
     * @param randomNode
     * @return
     */
    public static TreeNode succeedNode(TreeNode randomNode) {

        if (null == randomNode) {
            return null;
        }


        return null;
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode();

        TreeNode left1 = new TreeNode();
        TreeNode right1 = new TreeNode();

        TreeNode left1left = new TreeNode();
        TreeNode left1right = new TreeNode();

        TreeNode right1left = new TreeNode();
        TreeNode right1right = new TreeNode();


        root.setVal(1);
        root.setLeft(left1);
        root.setRight(right1);
        root.setParent(null);

        left1.setVal(2);
        left1.setLeft(left1left);
        left1.setRight(left1right);
        left1.setParent(root);

        right1.setVal(3);
        right1.setLeft(right1left);
        right1.setRight(right1right);
        right1.setParent(root);

        left1left.setVal(4);
        left1left.setLeft(null);
        left1left.setRight(null);
        left1left.setParent(left1);


        left1right.setVal(5);
        left1right.setLeft(null);
        left1right.setRight(null);
        left1right.setParent(left1);


        right1left.setVal(6);
        right1left.setLeft(null);
        right1left.setRight(null);
        right1left.setParent(right1);


        right1right.setVal(7);
        right1right.setLeft(null);
        right1right.setRight(null);
        right1right.setParent(right1);

//        System.out.println(findRoot(left1left).val);
//        System.out.println(findRootByRecursive(left1left).val);
//
//        System.out.println();
//
//        List<TreeNode> ans = new ArrayList<>();
//        in(root, ans);
//        for (TreeNode node : ans) {
//            System.out.print(node.val + " ");
//        }
        Assert.check(violentSolution(right1right) == violentSolution(right1right),"异常");
    }
}
