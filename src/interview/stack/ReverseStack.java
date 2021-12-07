package interview.stack;

import java.util.Stack;

/**
 * 翻转栈
 */
public class ReverseStack {

    /**
     * 保持stack整体结构不变，获取栈底元素
     * 1 -> 2 -> 3 == 1 -> 2 return 3
     *
     * @param stack
     * @return
     */
    public static int unchangedStructureFindBottom(Stack<Integer> stack) {
        Integer curValue = stack.pop();
        if (stack.isEmpty()) {
            return curValue;
        }
        int buttonValue = unchangedStructureFindBottom(stack);
        stack.push(curValue);
        return buttonValue;
    }


    /**
     * 翻转流程
     *
     * @param stack
     */
    public static void process(Stack<Integer> stack) {
        //  栈空返回
        if (stack.isEmpty()) {
            return;
        }
        //  获取栈底元素 保持栈结构不变
        int value = unchangedStructureFindBottom(stack);
        //  继续递归获取底部元素。当前行返回情况 - 栈空了
        process(stack);
        //  当前元素入栈
        stack.push(value);
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(3);
        stack.add(2);
        stack.add(1);


        process(stack);
        while (!stack.isEmpty()) {
            System.out.print(" " + stack.pop());
        }

    }
}
