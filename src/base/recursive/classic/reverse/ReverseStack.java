package base.recursive.classic.reverse;

import leetcode.util.LCUtil;

import java.util.Stack;

public class ReverseStack {

    /**
     * reverse1 -> last = 3, reverse2
     * reverse2 -> last = 2, reverse3
     * reverse3 -> last = 1, reverse4
     * reverse4 -> return
     * reverse3 -> last = 1, stack.push(1) return
     * reverse2 -> last = 2, stack.push(2) return
     * reverse1 -> last = 3, stack.push(3) return
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int last = f(stack);
        reverse(stack);
        stack.add(last);
    }

    /**
     * 返回栈底元素, 其他元素位置不变
     *
     * f1 -> result = 1, last = f2
     * f2 -> result = 2, last = f3
     * f3 -> result = 3, return 3
     * f2 -> result = 2, last = 3, stack.push(2), return 3
     * f1 -> result = 1, last = 3, stack.push(1), return 3
     *
     *
     * @param stack
     * @return
     */
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }
        int last = f(stack);
        stack.push(result);
        return last;
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();
        stack.add(3);
        stack.add(2);
        stack.add(1);
        reverse(stack);
        LCUtil.printStack(stack);
    }
}
