package leetcode.everyday.january;

import java.util.*;

public class January_21_minJumps {

    public static int minJumps(int[] arr) {
        int N = arr.length;
        Map<Integer, List<Integer>> idxSameValue = new HashMap<>();
        for (int i = 0; i < N; i++) {
            idxSameValue.putIfAbsent(arr[i], new ArrayList<>());
            idxSameValue.get(arr[i]).add(i);
        }

        Set<Integer> visitedIndex = new HashSet<>();
        visitedIndex.add(0);

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] idxStep = queue.poll();
            int idx = idxStep[0];
            int step = idxStep[1];

            if (idx == N - 1) {
                return step;
            }

            int value = arr[idx];
            step++;

            if (idxSameValue.containsKey(value)) {
                for (int nextIndex : idxSameValue.get(value)) {
                    if (visitedIndex.add(nextIndex)) {
                        queue.offer(new int[]{nextIndex, step});
                    }
                }
                idxSameValue.remove(value);
            }

            if (idx + 1 < N && visitedIndex.add(idx + 1)) {
                queue.offer(new int[]{idx + 1, step});
            }

            if (idx - 1 >= 0 && visitedIndex.add(idx - 1)) {
                queue.offer(new int[]{idx - 1, step});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        System.out.println(minJumps(arr));
    }
}
