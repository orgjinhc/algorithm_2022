package base.recursive.classic.dp.从左到右模型;

/**
 * 从左往右递归模型
 */
public class 背包问题 {

    public static void main(String[] args) {
        int[] weights = {5000, 1000, 500, 100};
        int[] values = {120, 40, 12, 3};
        int bag = 200;
        System.out.println(maxValue(weights, values, bag));
    }

    /**
     * 暴力尝试解
     *
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);
    }

    /**
     * @param w     重量
     * @param v     价值
     * @param index 当前来到index位置, 0 ~ index位置已经做过选择, 继续尝试 index ~ w.length 位置
     * @param rest  当前背包剩余空间
     * @return
     */
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
        int p2 = 0;
        //  有货有空间，计算当前货物价值，继续往下递归
        //  先尝试下个位置的货物是否能放进背包内, 如果满足条件再放入背包
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            //  有货有空间，计算当前货物价值
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

}
