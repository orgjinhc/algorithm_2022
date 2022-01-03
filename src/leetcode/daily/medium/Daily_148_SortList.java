package leetcode.daily.medium;

import leetcode.util.ListNode;

public class Daily_148_SortList {

    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode mid = getMid(head);
        if (mid.next == null) {
            return mid;
        }
        ListNode headB = mid.next;
        mid.next = null;
        return mergeTwoList(sortList(head), sortList(headB));
    }

    public ListNode getMid(ListNode head) {
        head = new ListNode(0, head);
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode dump = new ListNode();
        ListNode last = dump;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                last.next = l1;
                l1 = l1.next;
            } else {
                last.next = l2;
                l2 = l2.next;
            }
            last = last.next;
        }
        if (l1 != null) {
            last.next = l1;
        }
        if (l2 != null) {
            last.next = l2;
        }
        return dump.next;
    }

}
