package leetcode.hot_100;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class Hot_23_MergeKLists {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 边界的处理
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        //  []
        if (null == lists || lists.length < 1) {
            return null;
        }
        PriorityQueue<ListNode> ans = new PriorityQueue<>(Comparator.comparingInt((o) -> o.val));
        for (ListNode curNode : lists) {
            while (curNode != null) {
                ans.add(curNode);
                curNode = curNode.next;
            }
        }

        //  [[]]...[[][][]...]
        if (ans.isEmpty()) {
            return null;
        }
        ListNode head = ans.poll();
        ListNode pre = head;
        while (!ans.isEmpty()) {
            ListNode next = ans.poll();
            pre.next = next;
            pre = next;
        }
        pre.next = null;
        return head;
    }

    public static void main(String[] args) {

    }
}
