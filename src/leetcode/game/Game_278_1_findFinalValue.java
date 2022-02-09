package leetcode.game;

import java.util.HashMap;

public class Game_278_1_findFinalValue {

    public static int findFinalValue(int[] nums, int original) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        for (int num : nums) {
            dp.put(num, num);
        }

        return f(dp, original);
    }

    public static int f(HashMap<Integer, Integer> dp, int original) {
        if (!dp.containsKey(original)) {
            return original;
        }
        return f(dp, original * 2);
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 6, 1, 12};
        int original = 3;
        System.out.println(findFinalValue(nums, original));
    }

}
