package base.tree.most.value;

import base.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {

    public static void main(String[] args) {

    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (null == root) return ans;

        process(ans, new ArrayList<>(), root, 0, targetSum);

        return ans;
    }

    /**
     * 递归实现
     *
     * @param x
     * @param preSum
     * @param targetSum
     */
    public static void process(List<List<Integer>> ans, List<Integer> path, TreeNode x, int preSum, int targetSum) {

        if (x.left == null && x.right == null) {
            if (x.val + preSum == targetSum) {
                List<Integer> copy = new ArrayList<>();
                for (Integer integer : path) {
                    copy.add(integer);
                }
                copy.add(x.val);
                ans.add(copy);
                return;
            }
        }

        //  添加当前节点路径
        path.add(x.val);
        //  非叶子节点, 累加当前节点继续往下传preSum
        preSum += x.val;

        if (x.left != null) {
            process(ans, path, x.left, preSum, targetSum);
        }

        if (x.right != null) {
            process(ans, path, x.right, preSum, targetSum);
        }
        //  非叶节点返回, 也需要清理现场
        path.remove(path.size() - 1);
    }
}