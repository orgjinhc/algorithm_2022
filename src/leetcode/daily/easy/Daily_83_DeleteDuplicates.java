package leetcode.daily.easy;

import leetcode.util.ListNode;

/**
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 * <p>
 * 返回同样按升序排列的结果链表。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 * <p>
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 */
public class Daily_83_DeleteDuplicates {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode tmp = head;
        while (head != null && head.next != null) {
            if (head.val != head.next.val) {
                head = head.next;
            } else {
                head.next = head.next.next;
            }
        }
        return tmp;
    }

    public static void main(String[] args) {

    }
}
