package leetcode.daily;

/**
 * 颠倒给定的 32 位无符号整数的二进制位。
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-bits
 */
public class ReverseBits_190 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        // n的高16位，和n的低16位，交换
        n = (n >>> 16) | (n << 16);
        //  f = 1111
        //  0 = 0000
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);

        //  f = 1111
        //  0 = 0000
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);

        //  c = 1100
        //  3 = 0011
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);

        //  a = 1010
        //  5 = 0101
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }
}
