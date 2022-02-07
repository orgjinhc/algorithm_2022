package leetcode.everyday.january;

import java.util.Arrays;

public class January_28_numberOfWeakCharacters {

    /**
     * 攻击降序, 相同攻击在同一组内并且防御按照升序排序
     * 如果存在一个角色防御属性大于当前角色, 那么他一定是来自一个攻击力更强的组内, 当前角色定义为弱角色
     *
     * @param properties
     * @return
     */
    public static int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (o1, o2) -> o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]));
        int maxDef = 0;
        int ans = 0;
        for (int[] p : properties) {
            if (p[1] < maxDef) {
                ans++;
            } else {
                maxDef = p[1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] properties = {{7, 7}, {1, 2}, {9, 7}, {7, 3}, {3, 10}, {9, 8}, {8, 10}, {4, 3}, {1, 5}, {1, 5}};
        System.out.println(numberOfWeakCharacters(properties));
    }
}