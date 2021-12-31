package leetcode.daily.easy;

import java.util.HashMap;

public class Daily_167_twoSum {

    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> dp = new HashMap<>();
        dp.put(numbers[0], 0);
        for (int i = 1; i < numbers.length; i++) {
            if (!dp.containsKey(numbers[i])) {
                dp.put(numbers[i], i);
            }
            if (dp.containsKey(target - numbers[i])) {
                return new int[]{dp.get(target - numbers[i]), i};
            }
        }
        return new int[2];
    }
}
