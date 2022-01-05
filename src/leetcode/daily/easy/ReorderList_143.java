package leetcode.daily.easy;


import leetcode.util.ListNode;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reorder-list
 */
public class ReorderList_143 {

    /**
     * 传统做法
     * 用集合实现, 先把整个链表加入集合. 头指向尾, 尾连接头的下一个, 头尾指针碰撞了返回并删除
     *
     * @param l1
     * @return
     */
    public static ListNode reorderList(ListNode l1) {
        ListNode middleNode = getMiddleNode(l1);
        ListNode l2 = revertNode(middleNode);
        middleNode.next = null;
        mergeList(l1, l2);
        return l1;
    }

    /**
     * 合并链表
     *
     * @param l1
     * @param l2
     */
    public static void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }

    /**
     * 获取上中位节点
     *
     * @param head
     * @return
     */
    private static ListNode getMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 反转链表
     *
     * @param node
     * @return
     */
    public static ListNode revertNode(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode middleNode2 = getMiddleNode(node1);
        ListNode l1 = node1;
        ListNode l2 = middleNode2.next;
        middleNode2.next = null;

        while (l1 != null) {
            System.out.print(" " + l1.val);
            l1 = l1.next;
        }
        System.out.println();
        while (l2 != null) {
            System.out.print(" " + l2.val);
            l2 = l2.next;
        }
        System.out.println();
    }
}
