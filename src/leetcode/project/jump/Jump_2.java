package leetcode.project.jump;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Jump_2 {

    public static int canJump(int[] arr) {
        int N = arr.length;
        if (N == 1) {
            return 0;
        }
        //  BFS
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        //  配套HashSet
        Set<Integer> visitedIndex = new HashSet<>();
        visitedIndex.add(0);

        while (!queue.isEmpty()) {
            //  位置
            int[] indexSteep = queue.poll();
            int index = indexSteep[0];
            //  位置对应的步长
            int step = indexSteep[1];

            //  jumpLength
            int jumpLength = index + arr[index];
            if (jumpLength >= N - 1) {
                return step + 1;
            }

            for (int i = jumpLength; i > index; i--) {
                if (arr[i] != 0 && !visitedIndex.contains(i)) {
                    visitedIndex.add(i);
                    queue.add(new int[]{i, step + 1});
                    continue;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(canJump(nums));
    }
}