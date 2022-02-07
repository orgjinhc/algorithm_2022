package leetcode.everyday.january;

import leetcode.util.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class January_16_getRandom {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    ListNode root = null;
    int size = 0;

    public January_16_getRandom(ListNode head) {
        root = head;
        while (head != null) {
            map.put(size++, head.val);
            head = head.next;
        }
    }

    public int getRandom() {
        int index = new Random().nextInt(size);
        return map.get(index);
    }
}
