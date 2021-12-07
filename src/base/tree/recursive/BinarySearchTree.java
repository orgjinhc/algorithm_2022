package base.tree.recursive;

import base.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        root.setLeft(new TreeNode(2));
        root.setRight(new TreeNode(3));
        root.getLeft().setLeft(new TreeNode(4));
        root.getLeft().setRight(new TreeNode(5));
        root.getRight().setLeft(new TreeNode(6));
        root.getRight().setRight(new TreeNode(7));

        /**
         * method 1 暴力穷举
         */
        List<Integer> ans = new ArrayList<>();
        midIter(ans, root);
        for (int i = 1; i < ans.size(); i++) {
            if (ans.get(i) <= ans.get(i - 1)) {
                throw new RuntimeException("非二叉搜索树");
            }
        }

        /**
         * 递归实现
         */
        System.out.println(process(root).isBST);
    }

    /**
     * 中序遍历方式
     *
     * @param ans
     * @param root
     * @return
     */
    public static List<Integer> midIter(List<Integer> ans, TreeNode root) {
        if (null == root) {
            return ans;
        }

        midIter(ans, root.left);
        ans.add(root.val);
        midIter(ans, root.right);
        return ans;
    }


    /**
     * 递归方式
     */
    public static Info process(TreeNode x) {
        //  1.递归边界逻辑
        if (null == x) {
            //  与平衡树比较来看, 返回Info无法指定具体的max和min, 所以由上层对返回结果进行处理
            return null;
        }

        //  2.依次进行递归，收集左右子树信息
        Info leftBST = process(x.left);
        Info rightBST = process(x.right);

        int curNodeVal = x.val;

        //  3.定义好需要返回的Info对象, 在下面逻辑处理公用逻辑
        int max = curNodeVal;
        int min = curNodeVal;
        boolean isBST = true;


        //  通过返回的Info信息来判断，继续通过当前节点值和子树节点值，按语义进行比较
        //  4.如果左右子树只要有一个不是搜索子树, 整课树就不是搜索树
        if ((null != leftBST && !leftBST.isBST) || (null != rightBST && !rightBST.isBST)) {
            isBST = false;
        }

        //  5.继续比较节点大小来判断是否是搜索树 = left max < x < right min 成立
        boolean leftMaxLessX = null == leftBST ? true : (curNodeVal > leftBST.max);
        boolean rightMinMoreX = null == rightBST ? true : (curNodeVal < rightBST.min);

        //  根据比较的结果来定义当前树是否是搜索树
        if (!(leftMaxLessX && rightMinMoreX)) {
            isBST = false;
        }

        //  返回当前节点的max和min, 需要与两棵子树进行比较
        if (null != leftBST) {
            max = Math.max(max, leftBST.max);
            min = Math.min(min, leftBST.min);
        }

        if (null != rightBST) {
            max = Math.max(max, rightBST.max);
            min = Math.min(min, rightBST.min);
        }
        return new Info(max, min, isBST);
    }


    static class Info {
        boolean isBST;
        int max;
        int min;

        Info(int max, int min, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }
}