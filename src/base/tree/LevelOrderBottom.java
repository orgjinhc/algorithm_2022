package base.tree;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

public class LevelOrderBottom {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
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
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(0);
        root.setLeft(new TreeNode(1));
        root.setRight(new TreeNode(2));

        System.out.println(levelOrderRevert(root));

    }


    /**
     * 层级收集节点信息, 最后在进行反转操作
     * 如何收集
     * 使用队列逐层添加，每层只关心队列size，下次出队size个元素
     * 每个出队的元素依次添加自己的孩子节点到队列，先左后右
     *
     * @return
     */
    public static List<List<Integer>> levelOrderRevert(TreeNode head) {
        if (null == head) {
            return null;
        }

        LinkedList<List<Integer>> ans = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //  首次添加根节点
        queue.add(head);
        while (!queue.isEmpty()) {
            //  获取需要出队元素大小, 也决定了当前层级的元素个数
            int size = queue.size();

            //  依次获取当前层级下所有节点
            ArrayList<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                levelList.add(curNode.getVal());

                //  判断当前层级的节点是否存在孩子节点, 存在就加入queue
                if (null != curNode.left) {
                    queue.add(curNode.getLeft());
                }
                if (null != curNode.getRight()) {
                    queue.add(curNode.getRight());
                }
            }
            ans.add(0, levelList);
        }
        return ans;
    }


}
