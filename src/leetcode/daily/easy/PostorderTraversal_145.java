package leetcode.daily.easy;

import base.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PostorderTraversal_145 {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    public static void process(TreeNode root, List<Integer> ans) {
        if (root == null) {
            return;
        }

        process(root.left, ans);
        process(root.right, ans);
        ans.add(root.val);
    }
}
