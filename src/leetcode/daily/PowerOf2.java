package leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个num 它一定是2的x次幂, 求x大小
 */
public class PowerOf2 {


    public static int powerOf2(int num) {
        if (num <= 1) {
            return 0;
        }

        int baseNum = 1;
        Map<Integer, Integer> ans = new HashMap<>();
        ans.put(1, 0);
        for (int i = 1; i < 31; i++) {
            ans.put(baseNum << i, i);
        }
        return ans.get(num);
    }

    public static void main(String[] args) {
        System.out.println(powerOf2(1 << 10));
    }
}
