package leetcode.daily;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：
 * <p>
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为 1，那么这个数就是快乐数。
 * <p>
 * 链接：https://leetcode-cn.com/problems/happy-number
 */
public class IsHappy_202 {
    public boolean isHappy(int n) {
        while (n != 1 && n != 4) {
            int sum = 0;
            //  用于计算n每一位上的数开方相加的结果赋值给sum
            while (n != 0) {
                //  17 % 10 = 7
                //  17 / 10 = 1
                //  1 % 10 = 1
                //  sum = 1^2 + 7^2
                int index = n % 10;
                sum += index * index;
                //  抹掉一位
                n /= 10;
            }
            //  n 重新赋值
            n = sum;
        }
        return n == 1;
    }
}
