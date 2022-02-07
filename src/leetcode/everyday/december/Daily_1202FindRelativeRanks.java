package leetcode.everyday.december;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * <p>
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
 * <p>
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 * <p>
 * 链接：https://leetcode-cn.com/problems/relative-ranks
 */
public class Daily_1202FindRelativeRanks {


    /**
     * 思路
     * copy出新数组并排序，将排序的结果存储入Map<当前分值, 排序结果>
     * 新数组取出当前值所处位置即为答案
     *
     * @param arr
     * @return
     */
    public static String[] findRelativeRanks(int[] arr) {
        if (null == arr || arr.length < 1) {
            return null;
        }
        if (arr.length < 2) {
            return new String[]{"Gold Medal"};
        }

        int[] copyArr = arr.clone();
        Arrays.sort(copyArr);

        int N = copyArr.length;
        Map<Integer, Integer> map = new HashMap<>(N);
        for (int i = N - 1; i >= 0; i--) {
            map.put(copyArr[i], N - i);
        }

        String[] ans = new String[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int rank = map.get(arr[i]);
            if (rank == 1) {
                ans[i] = "Gold Medal";
            } else if (rank == 2) {
                ans[i] = "Silver Medal";
            } else if (rank == 3) {
                ans[i] = "Bronze Medal";
            } else {
                ans[i] = rank + "";
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] score = {10,3,8,9,4};
        String[] relativeRanks = findRelativeRanks(score);

        for (String relativeRank : relativeRanks) {
            System.out.print(" " + relativeRank);
        }
        System.out.println();
    }
}
