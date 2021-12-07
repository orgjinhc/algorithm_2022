public class BinaryTreeComparator {


    static class Node {
        Node left;
        Node right;
        int val;
    }

    public static boolean containsTree(Node head1, Node head2) {
        //  包含树为空返回false
        if (head1 == null) {
            return false;
        }

        //  被包含树为空返回true
        if (head2 == null) {
            return true;
        }

        //  两棵树是否从头节点开始就相同
        if (isSameTree(head1, head2)) {
            return true;
        }

        //  大树分别从左右开始判断是否包含小树
        return containsTree(head1.left, head2) || containsTree(head2.right, head2);
    }


    private static boolean isSameTree(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return true;
        }

        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }

        if (head1.val != head2.val) {
            return false;
        }

        //  两棵树的左右子树都必须满足当前函数的逻辑，才能认为两棵树相同
        return isSameTree(head1.left, head2.left) && isSameTree(head1.right, head2.right);
    }


    public static void main(String[] args) {

    }
}
