package leetcode.daily.everyday.january;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class January_18_findMinDifference {

    public static int findMinDifferenceBySort(List<String> timePoints) {
        final int finalSize = 24 * 60;
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int firstMinutes = getMinutes(timePoints.get(0));
        int preMinutes = firstMinutes;
        for (int i = 1; i < timePoints.size(); i++) {
            int secondMinutes = getMinutes(timePoints.get(i));
            ans = Math.min(ans, secondMinutes - preMinutes);
            preMinutes = secondMinutes;
        }
        return Math.min(ans, finalSize + firstMinutes - preMinutes);
    }

    public static int getMinutes(String timePoint) {
        String[] split = timePoint.split(":");
        return Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
    }

    public static int findMinDifference(List<String> timePoints) {
        PriorityQueue<Pair> queue = new PriorityQueue<>((o1, o2) -> o1.hour == o2.hour ? o1.minutes - o2.minutes : o1.hour - o2.hour);
        for (String timePoint : timePoints) {
            String[] timePointStr = timePoint.split(":");
            Pair pair = new Pair(Integer.valueOf(timePointStr[0]), Integer.valueOf(timePointStr[1]));
            queue.add(pair);
        }
        int ans = Integer.MAX_VALUE;
        Pair head = queue.poll();
        Pair firstPair = head;
        while (!queue.isEmpty()) {
            Pair secondPair = queue.poll();
            ans = Math.min(60 * (secondPair.hour - firstPair.hour) + secondPair.minutes - firstPair.minutes, ans);
            firstPair = secondPair;
        }
        ans = Math.min(60 * (23 + head.hour - firstPair.hour) + (60 + head.minutes) - firstPair.minutes, ans);
        return ans;
    }

    static class Pair {
        int hour;
        int minutes;

        public Pair(int hour, int minutes) {
            this.hour = hour;
            this.minutes = minutes;
        }
    }

    public static void main(String[] args) {
        List<String> timePoints = Arrays.asList("00:00", "23:59", "00:00");

        System.out.println(findMinDifference(timePoints));
    }
}
