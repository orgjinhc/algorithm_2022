package leetcode.hot_100;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthFromEnd_19 {

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

        //  遍历node, 求出个数
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
        //  得到要删除位置的前一个位置
        int delIndex = count - n;
        int index = 1;
        while (index < delIndex) {
            index++;
            pre = pre.next;
        }

        pre.next = pre.next.next;
        return head;
    }

    public static void main(String[] args) {

    }
}
