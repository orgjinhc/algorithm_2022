package leetcode.hot_100;

import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop()'—— 删除栈顶的元素。
 * top()'—— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * 链接：https://leetcode-cn.com/problems/min-stack
 */
public class MinStack_155 {

    // 数组栈, [当前值, 栈内最小值]
    private Stack<int[]> stack = new Stack<>();

    public MinStack_155() {

    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(new int[]{x, x});
        } else {
            stack.push(new int[]{x, Math.min(x, stack.peek()[1])});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }

    /**
     * ["MinStack","push","push","push","getMin","pop","top","getMin"]
     * [[],[-2],[0],[-3],[],[],[],[]]
     * <p>
     * [null,null,null,null,-3,null,0,-2]
     */
    public static void main(String[] args) {
        MinStack_155 minStack_155 = new MinStack_155();
        minStack_155.push(-2);
        minStack_155.push(0);
        minStack_155.push(-3);
        System.out.println(minStack_155.getMin());
        minStack_155.pop();
        System.out.println(minStack_155.top());
        System.out.println(minStack_155.getMin());
    }
}