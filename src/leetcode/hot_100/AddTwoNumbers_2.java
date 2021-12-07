package leetcode.hot_100;

import java.util.Stack;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
 * <p>
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */
public class AddTwoNumbers_2 {

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

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode ansNode = new ListNode();
        ListNode pre = ansNode;
        int base = 0;
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
            pre.val = sum % 10;
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

    /**
     * 可以利用栈结构预处理两个链表，依次弹出栈内数据进行相加，进位信息单独存储并参与运算
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //  处理边界
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        //  预处理
        Stack<Integer> l1Stack = new Stack<>();
        Stack<Integer> l2Stack = new Stack<>();
        //  判断每个链表的长度, 最大长度作为base节点
        ListNode l1B = l1;
        ListNode l2B = l2;

        while (l1B != null) {
            l1Stack.add(l1B.val);
            l1B = l1B.next;
        }
        while (l2B != null) {
            l2Stack.add(l2B.val);
            l2B = l2B.next;
        }


        ListNode ansNode = new ListNode();
        ListNode pre = ansNode;
        int base = 0;
        while ((!l1Stack.isEmpty() && !l1Stack.isEmpty()) || base != 0) {
            Integer num1 = 0;
            if (!l1Stack.isEmpty()) {
                num1 = l1Stack.pop();
            }

            Integer num2 = 0;
            if (!l2Stack.isEmpty()) {
                num2 = l2Stack.pop();
            }

            int sum = base + (num1 == null ? 0 : num1) + (num2 == null ? 0 : num2);
            base = (sum) / 10;
            pre.val = (sum) % 10;

            if (!l1Stack.isEmpty() || !l1Stack.isEmpty() || base != 0) {
                ListNode curNode = new ListNode();
                pre.next = curNode;
                pre = curNode;
            }
        }
        return ansNode;
    }

    public static void main(String[] args) {

        ListNode l1 = new ListNode(0);
//        ListNode l11 = new ListNode(4);
//        ListNode l111 = new ListNode(9);
//        l1.next = l11;
//        l11.next = l111;

        ListNode l2 = new ListNode(7);
        ListNode l22 = new ListNode(3);
//        ListNode l222 = new ListNode(4);
//        ListNode l2222 = new ListNode(9);

        l2.next = l22;
//        l22.next = l222;
//        l222.next = l2222;


        ListNode listNode = addTwoNumbers2(l1, l2);

        while (listNode != null) {
            System.out.print("  " + listNode.val);
            listNode = listNode.next;
        }
        System.out.println();
    }
}
