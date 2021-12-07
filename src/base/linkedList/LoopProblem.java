package base.linkedList;

import java.util.HashSet;

public class LoopProblem {

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 判断是否有环
     * 空节点、一个节点、两个节点情况下都无法构成环结构
     * 构成环的前提是满足三个节点(单链表)
     *
     * @param head
     * @return
     */
    public static boolean isLoopWithOutNode(Node head) {
        if (null == head || head.next == null || head.next.next == null) {
            return false;
        }

        //  利用容器实现 - 简单、空间复杂度高
        HashSet<Node> set = new HashSet<>();

        Node root = head;
        while (root != null) {
            if (!set.add(root)) {
                return true;
            }
            root = root.next;
        }
        return false;
    }

    /**
     * 判断是否有环
     * 空节点、一个节点、两个节点情况下都无法构成环结构
     * 构成环的前提是满足三个节点(单链表)
     *
     * @param head
     * @return
     */
    public static Node isLoop(Node head) {
        if (null == head || head.next == null || head.next.next == null) {
            return null;
        }

        //  利用容器实现 - 简单、空间复杂度高
        HashSet<Node> set = new HashSet<>();

        Node root = head;
        while (root != null) {
            if (!set.add(root)) {
                return root;
            }
            root = root.next;
        }
        return null;
    }

    /**
     * 判断是否有环
     * 空节点、一个节点、两个节点情况下都无法构成环结构
     * 构成环的前提是满足三个节点(单链表)
     *
     * @param head
     * @return
     */
    public static Node isLoopBySF(Node head) {
        if (null == head || head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (null == slow || null == fast) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }

    /**
     * 判断是否有环
     * 空节点、一个节点、两个节点情况下都无法构成环结构
     * 构成环的前提是满足三个节点(单链表)
     *
     * @param head
     * @return
     */
    public static boolean isLoopBySFWithOutNode(Node head) {
        if (null == head || head.next == null || head.next.next == null) {
            return false;
        }

        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (null == slow || null == fast) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


    public static void main(String[] args) {
        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        //  0>1>2>3>1
        root.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node1);

        System.out.println(isLoopBySF(root).val);

    }
}