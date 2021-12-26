package leetcode.hot_100;

import leetcode.ListNode;
import leetcode.SortUtil;

import java.util.Stack;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
 * <p>
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */
public class AddTwoNumbers_2 {

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode ansNode = new ListNode();
        ListNode pre = ansNode;
        int base = 0;

        //  链表相加行为
        while (l1 != null || l2 != null) {
            int num1 = 0;
            if (l1 != null) {
                num1 = l1.val;
                l1 = l1.next;
            }

            int num2 = 0;
            if (l2 != null) {
                num2 = l2.val;
                l2 = l2.next;
            }

            int sum = base + num1 + num2;
            base = sum / 10;
            //  设置返回值
            pre.val = sum % 10;

            //  保证两个链表都为null的情况, 无法继续创建下一节点
            if (l1 != null || l2 != null) {
                pre.next = new ListNode();
                pre = pre.next;
            }
        }

        if (base == 1) {
            pre.next = new ListNode(base);
        }

        return ansNode;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(9);
        ListNode head2 = new ListNode(9);
        ListNode head3 = new ListNode(9);
        ListNode head4 = new ListNode(9);
        ListNode head5 = new ListNode(9);
        ListNode head6 = new ListNode(9);
        ListNode head7 = new ListNode(9);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        head5.next = head6;
        head6.next = head7;

        ListNode tail1 = new ListNode(9);
        ListNode tail2 = new ListNode(9);
        ListNode tail3 = new ListNode(9);
        ListNode tail4 = new ListNode(9);
        tail1.next = tail2;
        tail2.next = tail3;
        tail3.next = tail4;

        SortUtil.print(addTwoNumbers2(head1, tail1));
    }
}