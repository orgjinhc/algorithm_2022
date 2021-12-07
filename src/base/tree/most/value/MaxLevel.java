package base.tree.most.value;


import base.tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MaxLevel {


    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        node.setLeft(node1);
        node.setRight(node2);

        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);

        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);

        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(7);

        node3.setLeft(node7);
        node3.setRight(node8);
        System.out.println(process2(node));
    }

    /**
     * 深度优先遍历方式2
     * 利用队列FIFO特性，每次入队都是从左向右的顺序进行，保证每一层结束的时刻同时也完成了下一层的入队并有队列的特性
     *
     * @param node
     */
    private static int process1(TreeNode node) {
        if (null == node) {
            System.out.println("空树, 无需遍历");
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        int max = 0;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            //  当前队列里元素的个数, 也代表了每一层的节点数量
            curLevelNodes = queue.size();
            max = Math.max(max, curLevelNodes);
            for (int i = 0; i < curLevelNodes; i++) {
                TreeNode curNode = queue.poll();
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }


    /**
     * 利用map + level 指针
     *
     * @param node
     */
    private static int process2(TreeNode node) {
        if (null == node) {
            System.out.println("空树, 无需遍历");
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        HashMap<TreeNode, Integer> map = new HashMap<>();
        int curLevel = 1;
        map.put(node, curLevel);
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            Integer curNodeLevel = map.get(curNode);
            if (curNode.left != null) {
                queue.add(curNode.left);
                //  下层层级由当前层决定
                map.put(curNode.left, curNodeLevel + 1);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                map.put(curNode.right, curNodeLevel + 1);
            }

            //  每一个层(大层级)边界, 通过节点对应的层级和全局层级动态调整
            if (curNodeLevel == curLevel) {
                //  当前节点所在层级属于大层级的情况 节点数量 ++
                curLevelNodes++;
            } else {
                //  一大层边界确定后, 计算当前层的节点数量
                max = Math.max(curLevelNodes, max);
                //  当前大层级层数来到下一层
                curLevel++;
                //  当前层内节点数重置为1
                curLevelNodes = 1;
            }
        }
        max = Math.max(curLevelNodes, max);
        return max;
    }


    /**
     * 利用 curEnd + nextEnd + curNodes指针
     *
     * @param node
     */
    private static int process3(TreeNode node) {
        if (null == node) {
            System.out.println("空树, 无需遍历");
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        TreeNode curEnd = node;
        TreeNode nextEnd = null;
        int max = 0;
        int curLevelNodes = 0;
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            if (curNode.left != null) {
                queue.add(curNode.left);
                nextEnd = curNode.left;
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                nextEnd = curNode.right;
            }
            curLevelNodes++;
            if (curEnd == curNode) {
                max = Math.max(curLevelNodes, max);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }
}