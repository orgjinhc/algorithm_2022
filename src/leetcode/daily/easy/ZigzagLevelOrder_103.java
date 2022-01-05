package leetcode.daily.easy;

import leetcode.util.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 给定二叉树[3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层序遍历如下：
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 */
public class ZigzagLevelOrder_103 {


    /**
     * 1
     * 2       3
     * 4               5
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> curLevelNode = new LinkedList<>();
        curLevelNode.add(root);
        //  isHead 从双向链表的头获取元素, 同时从左向右把元素添加到链表尾部
        //  !isHead 从链表的尾部获取元素, 从右向左的把元素添加到链表头部
        //  保证插入和弹出都是两个方向
        boolean isHead = true;
        while (!curLevelNode.isEmpty()) {
            int size = curLevelNode.size();
            List<Integer> curLevelAns = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = isHead ? curLevelNode.pollFirst() : curLevelNode.pollLast();
                curLevelAns.add(curNode.val);
                if (isHead) {
                    if (curNode.left != null) {
                        curLevelNode.addLast(curNode.left);
                    }
                    if (curNode.right != null) {
                        curLevelNode.addLast(curNode.right);
                    }
                } else {
                    if (curNode.right != null) {
                        curLevelNode.addFirst(curNode.right);
                    }
                    if (curNode.left != null) {
                        curLevelNode.addFirst(curNode.left);
                    }
                }
            }
            isHead = !isHead;
            ans.add(curLevelAns);
        }
        return ans;
    }

    public static void revertArr(List<Integer> ans) {
        int L = 0;
        int R = ans.size() - 1;

        while (L <= R) {
            Integer tmp = ans.get(R);
            ans.set(R, ans.get(L));
            ans.set(L, tmp);
            L++;
            R--;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode leftLeft = new TreeNode(4);
        TreeNode rightLeft = new TreeNode(5);
        root.left = left;
        root.right = right;
        left.left = leftLeft;
        right.left = rightLeft;
        List<List<Integer>> ans = zigzagLevelOrder(root);


        for (List<Integer> an : ans) {
            for (Integer integer : an) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
