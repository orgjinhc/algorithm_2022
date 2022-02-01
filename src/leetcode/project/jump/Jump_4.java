package leetcode.project.jump;

import java.util.*;

public class Jump_4 {

    public static int minJumps(int[] arr) {
        int N = arr.length;
        if (N == 1) {
            return 0;
        }
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        //  相同值不同索引位置问题
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list;
            if (map.containsKey(arr[i])) {
                list = map.get(arr[i]);
            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            map.put(arr[i], list);
        }

        //  BFS
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        //  配套HashSet
        Set<Integer> visitedIndex = new HashSet<>();
        visitedIndex.add(0);

        while (!queue.isEmpty()) {
            int[] indexSteep = queue.poll();
            int index = indexSteep[0];
            //  移动的次数
            int step = indexSteep[1];
            if (index >= N - 1) {
                return step;
            }
            int left = index + 1;
            int right = index - 1;
            if (left < N && !visitedIndex.contains(left)) {
                visitedIndex.add(left);
                queue.add(new int[]{left, step + 1});
            }
            if (right >= 0 && !visitedIndex.contains(right)) {
                visitedIndex.add(right);
                queue.add(new int[]{right, step + 1});
            }
            int sameValueNextIndex = arr[index];
            if (!map.containsKey(sameValueNextIndex)) {
                continue;
            }
            for (Integer i : map.get(sameValueNextIndex)) {
                if (!visitedIndex.contains(i)) {
                    visitedIndex.add(i);
                    queue.add(new int[]{i, step + 1});
                }
            }
            map.remove(sameValueNextIndex);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        System.out.println(minJumps(nums));
    }
}