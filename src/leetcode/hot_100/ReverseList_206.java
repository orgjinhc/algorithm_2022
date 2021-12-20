package leetcode.hot_100;

import leetcode.ListNode;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 */
public class ReverseList_206 {

    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}