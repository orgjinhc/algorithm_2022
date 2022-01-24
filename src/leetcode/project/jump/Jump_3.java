package leetcode.project.jump;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Jump_3 {

    public static boolean canReach(int[] arr, int start) {
        int N = arr.length;
        if (start >= N) {
            return false;
        }
        if ((N == 1 && arr[0] == 0) || arr[start] == 0) {
            return true;
        }
        //  BFS
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        //  配套HashSet
        Set<Integer> visitedIndex = new HashSet<>();
        visitedIndex.add(start);

        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (index >= N || index < 0) {
                continue;
            }
            int step = arr[index];
            int left = index - step;
            int right = index + step;
            if ((left >= 0 && arr[left] == 0) || (right < N && arr[right] == 0)) {
                return true;
            }
            if (!visitedIndex.contains(left)) {
                visitedIndex.add(left);
                queue.add(left);
            }
            if (!visitedIndex.contains(right)) {
                visitedIndex.add(right);
                queue.add(right);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {4, 4, 1, 3, 0, 3};
        int start = 2;
        System.out.println(canReach(nums, start));
    }
}