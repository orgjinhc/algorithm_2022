package base.tree.most.value;


import base.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstTraversal {


    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        node.setLeft(node1);
        node.setRight(node2);

        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        bft1(node);
        System.out.println();
        bft2(node);
        System.out.println();
    }

    /**
     * 深度优先遍历方式1
     * 利用队列FIFO特性，每次入队都是从左向右的顺序进行，保证每一层结束的时刻同时也完成了下一层的入队并有队列的特性
     *
     * @param node
     */
    private static void bft1(TreeNode node) {
        if (null == node) {
            System.out.println("空树, 无需遍历");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            System.out.print(curNode.val + " ");
            if (curNode.left != null) {
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
            }
        }

    }

    /**
     * 深度优先遍历方式2
     * 利用队列FIFO特性，每次入队都是从左向右的顺序进行，保证每一层结束的时刻同时也完成了下一层的入队并有队列的特性
     *
     * @param node
     */
    private static void bft2(TreeNode node) {
        if (null == node) {
            System.out.println("空树, 无需遍历");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            //  当前队列里元素的个数, 也代表了每一层的节点数量
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                System.out.print(curNode.val + " ");
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
        }
    }
}