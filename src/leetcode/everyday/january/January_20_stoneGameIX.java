package leetcode.everyday.january;

public class January_20_stoneGameIX {

    public static boolean stoneGameIX(int[] stones) {
        int[] nums = new int[3];
        for (int i = 0; i < stones.length; i++) {
            nums[stones[i] % 3]++;
        }
        if (nums[0] % 2 == 0) {
            if (nums[1] > 0 && nums[2] > 0) {
                return true;
            }
            return false;
        }
        return Math.abs((nums[1] - nums[2])) > 2;
    }

    public static void main(String[] args) {
        int[] stones = {1, 1, 2, 3, 3, 3, 3};
        System.out.println(stoneGameIX(stones));
    }
}
