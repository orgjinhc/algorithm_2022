package leetcode.hot_100;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class Hot_19_RemoveNthFromEnd {

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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (null == head || n < 0) {
            return null;
        }
        ListNode tmp = head;
        int count = 0;
        while (tmp != null) {
            count++;
            tmp = tmp.next;
        }

        if (n > count) {
            return head;
        }

        if (n == count) {
            return head.next;
        }

        ListNode pre = head;
        int rightIndex = count - n;
        int leftIndex = 1;
        while (leftIndex < rightIndex) {
            leftIndex++;
            pre = pre.next;
        }

        pre.next = pre.next.next;
        return head;
    }

    public static void main(String[] args) {

    }
}
