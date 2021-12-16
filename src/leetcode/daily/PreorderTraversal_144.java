package leetcode.daily;

import base.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PreorderTraversal_144 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    public static void process(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }

        ans.add(root.val);
        process(root.left, ans);
        process(root.right, ans);
    }

}
