package leetcode.daily.everyday.december;

import java.util.Arrays;

/**
 * 冬季已经来临。'你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * <p>
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * <p>
 * 现在，给出位于一条水平线上的房屋'houses 和供暖器'heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * <p>
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 * <p>
 * 链接：https://leetcode-cn.com/problems/heaters
 */
public class Daily_475_FindRadius {

    /**
     * 比如地点是            7  9  14
     * 供暖点的位置: 1 3 4 5      13 15 17
     * 先看地点7
     * 由1供暖，半径是6
     * 由3供暖，半径是4
     * 由4供暖，半径是3
     * 由5供暖，半径是2
     * 由13供暖，半径是6
     * 由此可知，地点7应该由供暖点5来供暖，半径是2
     * 再看地点9
     * 供暖点不回退
     * 由5供暖，半径是4
     * 由13供暖，半径是4
     * 由15供暖，半径是6
     * 由此可知，地点9应该由供暖点13来供暖，半径是4
     * 为什么是13而不是5？因为接下来的地点都会更靠右，所以半径一样的时候，就应该选更右的供暖点
     * 再看地点14
     * 供暖点不回退
     * 由13供暖，半径是1
     * 由15供暖，半径是1
     * 由17供暖，半径是3
     * 由此可知，地点14应该由供暖点15来供暖，半径是1
     * 以此类推
     */
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        // 时间复杂度O(N)
        // i是地点，j是供暖点
        for (int i = 0, j = 0; i < houses.length; i++) {
            while (!best(houses, heaters, i, j)) {
                j++;
            }
            ans = Math.max(ans, Math.abs(heaters[j] - houses[i]));
        }
        return ans;
    }

    // 这个函数含义：
    // 当前的地点houses[i]由heaters[j]来供暖是最优的吗？
    // 当前的地点houses[i]由heaters[j]来供暖，产生的半径是a
    // 当前的地点houses[i]由heaters[j + 1]来供暖，产生的半径是b
    // 如果a < b, 说明j位置是最优，供暖不应该跳下一个位置
    // 如果a >= b, 说明j位置不是最优，应该跳下一个位置
    public static boolean best(int[] houses, int[] heaters, int i, int j) {
        return j == heaters.length - 1 || Math.abs(heaters[j] - houses[i]) < Math.abs(heaters[j + 1] - houses[i]);
    }

    public static void main(String[] args) {
        int[] houses = {1, 5};
        int[] heaters = {2};
        System.out.println(findRadius(houses, heaters));
    }
}