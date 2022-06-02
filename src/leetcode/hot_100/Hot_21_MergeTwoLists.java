package leetcode.hot_100;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class Hot_21_MergeTwoLists {

    public static class ListNode {
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
     * 合并有序链表
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     *
     * @param node1
     * @param node2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode node1, ListNode node2) {
        if (null == node1 && null == node2) {
            return null;
        }

        PriorityQueue<ListNode> ans = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        while (node1 != null || node2 != null) {
            if (node1 != null) {
                ans.add(node1);
                node1 = node1.next;
            }
            if (node2 != null) {
                ans.add(node2);
                node2 = node2.next;
            }
        }
        ListNode head = ans.poll();
        ListNode pre = head;
        while (!ans.isEmpty()) {
            pre.next = ans.poll();
            pre = pre.next;
        }
        pre.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(-10);
        ListNode node11 = new ListNode(-9);
        ListNode node111 = new ListNode(-6);
        ListNode node1111 = new ListNode(-4);
        ListNode node11111 = new ListNode(1);
        ListNode node111111 = new ListNode(9);
        ListNode node1111111 = new ListNode(9);
        node1.next = node11;
        node11.next = node111;
        node111.next = node1111;
        node1111.next = node11111;
        node11111.next = node111111;
        node111111.next = node1111111;
        ListNode node2 = new ListNode(-5);
        ListNode node22 = new ListNode(-3);
        ListNode node222 = new ListNode(0);
        ListNode node2222 = new ListNode(7);
        ListNode node22222 = new ListNode(8);
        ListNode node222222 = new ListNode(8);

        node2.next = node22;
        node22.next = node222;
        node222.next = node2222;
        node2222.next = node22222;
        node22222.next = node222222;
        ListNode ans = mergeTwoLists(node1, node2);

        while (ans != null) {
            System.out.print(ans.val + "   ");
            ans = ans.next;
        }
    }
}
