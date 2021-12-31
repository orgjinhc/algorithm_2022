package leetcode.hot_100;

import leetcode.util.ListNode;

import java.util.LinkedList;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2]
 * 输出：false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Hot_234_IsPalindrome_234 {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        if (head.next == null) {
            return true;
        }
        LinkedList<Integer> ans = new LinkedList<>();
        while (head != null) {
            ans.add(head.val);
            head = head.next;
        }

        while (!ans.isEmpty()) {
            Integer first = ans.pollFirst();
            Integer last = ans.pollLast();
            if (first == null || last == null) {
                return true;
            }
            if (first != last) {
                return false;
            }
        }
        return ans.isEmpty();
    }
}
