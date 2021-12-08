package base.recursive;

/**
 * 从左往右递归模型
 */
public class Beibao {

    public static int process(int[] w, int[] v, int index, int rest) {
        //  无空间
        if (rest < 0) {
            return -1;
        }
        //  无货
        if (index == w.length) {
            return 0;
        }
        //  有货有空间，不计算当前货物价值，继续往下递归
        int p1 = process(w, v, index + 1, rest);
        //  默认-1
        int p2 = -1;
        //  有货有空间，计算当前货物价值，继续往下递归
        int p2Next = process(w, v, index + 1, rest - w[index]);
        if (p2Next != -1) {
            //  有货有空间，计算当前货物价值
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    public static void main(String[] args) {
        int[] weights = {5000, 1000, 500, 100};
        int[] values = {120, 40, 12, 3};
        System.out.println(process(weights, values, 0, 2000));
    }
}
