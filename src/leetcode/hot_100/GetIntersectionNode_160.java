package leetcode.hot_100;

import leetcode.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个单链表的头节点headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * <p>
 * 图示两个链表在节点 c1 开始相交：
 * <p>
 * 题目数据 保证 整个链式结构中不存在环。
 * <p>
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * <p>
 * 自定义评测：
 * <p>
 * 评测系统 的输入如下（你设计的程序 不适用 此输入）：
 * <p>
 * intersectVal - 相交的起始节点的值。如果不存在相交节点，这一值为 0
 * listA - 第一个链表
 * listB - 第二个链表
 * skipA - 在 listA 中（从头节点开始）跳到交叉节点的节点数
 * skipB - 在 listB 中（从头节点开始）跳到交叉节点的节点数
 * 评测系统将根据这些输入创建链式数据结构，并将两个头节点 headA 和 headB 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 视作正确答案 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 */
public class GetIntersectionNode_160 {

    /**
     * 官方
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 相交节点
     * <p>
     * 8
     * [4,1,8,4,5]
     * [5,6,1,8,4,5]
     * 2
     * 3
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA || null == headB) {
            return null;
        }
        int heightDifference = 0;
        ListNode tmpA = headA;
        while (tmpA != null) {
            heightDifference++;
            tmpA = tmpA.next;
        }
        ListNode tmpB = headB;
        while (tmpB != null) {
            heightDifference--;
            tmpB = tmpB.next;
        }
        ListNode firstNode = heightDifference > 0 ? headA : headB;
        ListNode secondNode = firstNode == headA ? headB : headA;
        int abs = Math.abs(heightDifference);
        while (abs > 0) {
            abs--;
            firstNode = firstNode.next;
        }
        while (firstNode != secondNode) {
            if (firstNode == null || secondNode == null) {
                return null;
            }
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }
        return firstNode;
    }

    public static void main(String[] args) {
        ListNode headA = new ListNode(4);
        ListNode nodeA1 = new ListNode(1);
        ListNode nodeA2 = new ListNode(8);
        ListNode nodeA3 = new ListNode(4);
        ListNode nodeA4 = new ListNode(5);
        headA.next = nodeA1;
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;
        nodeA3.next = nodeA4;

        ListNode headB = new ListNode(5);
        ListNode nodeB1 = new ListNode(6);

        headB.next = nodeB1;
        nodeB1.next = nodeA1;
        nodeA1.next = nodeA2;
        nodeA2.next = nodeA3;
        nodeA3.next = nodeA4;
        System.out.println(getIntersectionNode(headA, headB));
        System.out.println(getIntersectionNode1(headA, headB));
    }
}
