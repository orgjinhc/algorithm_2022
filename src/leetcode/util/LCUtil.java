package leetcode.util;

import java.util.List;
import java.util.Stack;

public class LCUtil {

    public static void reverse(int[] nums, int L, int R) {
        while (L <= R) {
            swap(nums, L++, R--);
        }
    }

    public static void swap(int[] nums, int L, int R) {
        int tmp = nums[R];
        nums[R] = nums[L];
        nums[L] = tmp;
    }

    public static void swap(char[] nums, int L, int R) {
        char tmp = nums[R];
        nums[R] = nums[L];
        nums[L] = tmp;
    }

    public static void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void printList(List<String> nums) {
        for (String num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void printListInteger(List<Integer> nums) {
        for (Integer num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void printStack(Stack<Integer> stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
        System.out.println();
    }


    public static void print(List<List<Integer>> nums) {
        for (List<Integer> subList : nums) {
            for (Integer num : subList) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void print(ListNode listNode) {
        while (listNode != null) {
            System.out.print("  " + listNode.val);
            listNode = listNode.next;
        }
        System.out.println();
    }
}
