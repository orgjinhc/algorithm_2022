package base.recursive.classic;

/**
 * 汉诺塔问题
 * 根据此问题 推导出递归的本质
 */
public class 汉诺塔问题 {

    /**
     * 移动汉诺塔的主函数
     *
     * @param n 层级
     */
    public static void printHanoi(int n) {
        //  第一步流程:将 n-1 从 left 移动到 mid (当需要移动的数子的数量不为1的情况, 抽象此过程)
        leftToMid(n - 1);
        //  第二步流程:移动目标数到 right (当需要移动的数子的数量为1的情况, 实际操作过程, 真实发生移动数到目标位置)
        System.out.println("move " + n + " from left to right");
        //  第三步流程:将 n-1 从 mid 移动到 right (当需要移动的数子的数量不为1的情况, 抽象此过程)
        midToRight(n - 1);
    }

    /**
     * 汉诺塔问题抽象为子问题 - n个数从左移到右, 将所有数从左移动到右
     * <p>
     * 如果就一个数了直接按函数的含义直接移动即可
     * 否则进行如下流程划分
     * 先将 n-1 个数, 从左移动到中
     * 然后移动剩下的最后一个数
     * 最后再将 n-1 个数, 从中移动到右
     *
     * @param n
     */
    public static void leftToMid(int n) {
        //  base case: 不能再抽象的过程, 不能再划分的子问题、子过程, 当 n == 1 也等价于目前在移动的数就是最小的数, 可以随意移动的数, 直接操作
        if (n == 1) {
            System.out.println("move 1 from left to mid");
            return;
        }
        //  第一步流程:将 n-1 从 left 移动到 right (当需要移动的数子的数量不为1的情况, 抽象此过程)
        leftToRight(n - 1);
        //  第二步流程:移动目标数到 mid (当需要移动的数子的数量是1的情况, 实际操作过程)
        System.out.println("move " + n + " form left to mid");
        //  第三步流程:将 n-1 从 right 移动到 mid (当需要移动的数子的数量不为1的情况, 抽象此过程)
        rightToMid(n - 1);
    }

    /**
     * 汉诺塔问题抽象为子问题 - n个数从左移到右, 将所有数从左移动到右
     * <p>
     * 如果就一个数了直接按函数的含义直接移动即可
     * 否则进行如下流程划分
     * 先将 n-1 个数, 从左移动到中
     * 然后移动剩下的最后一个数
     * 最后再将 n-1 个数, 从中移动到右
     *
     * @param n
     */
    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 form left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("move " + n + " form left to right");
        midToRight(n - 1);
    }

    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 form right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("move " + n + " form right to mid");
        leftToMid(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 form right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("move " + n + " form right to left");
        midToLeft(n - 1);
    }


    public static void midToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    public static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("move " + n + " from mid to left");
        rightToLeft(n - 1);
    }


    /**
     * 通过改变递归函数的参数列表, 来增减递归函数的方法数
     * 2^n - 1
     *
     * @param n
     * @param from
     * @param to
     * @param other
     */
    public static void func(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
            return;
        }

        func(n - 1, from, other, to);
        System.out.println("move " + n + " from " + from + " to " + to);
        func(n - 1, other, to, from);
    }

    public static void main(String[] args) {
        printHanoi(3);
        System.out.println();
        func(3, "left", "right", "mid");


        String str = "abcdefg";
    }
}
