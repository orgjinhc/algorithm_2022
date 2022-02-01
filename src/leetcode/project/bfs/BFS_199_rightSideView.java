package leetcode.project.bfs;

import base.tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFS_199_rightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(root);
        while (!bfsQueue.isEmpty()) {
            int size = bfsQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = bfsQueue.poll();
                if (i == 0) {
                    ans.add(node.val);
                }
                if (node.right != null) {
                    bfsQueue.offer(node.right);
                }

                if (node.left != null) {
                    bfsQueue.offer(node.left);
                }
            }
        }
        return ans;
    }
}