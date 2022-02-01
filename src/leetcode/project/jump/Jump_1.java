package leetcode.project.jump;

import java.util.*;

public class Jump_1 {

    public static boolean canJumpByBSF(int[] arr) {
        int N = arr.length;
        if (N == 1) {
            return true;
        }
        //  BFS
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        //  配套HashSet
        Set<Integer> visitedIndex = new HashSet<>();
        visitedIndex.add(0);

        while (!queue.isEmpty()) {
            //  位置
            int index = queue.poll();
            //  位置对应的步长
            int step = arr[index];

            //  jumpLength
            int jumpLength = index + step;
            if (jumpLength >= N - 1) {
                return true;
            }

            if (arr[jumpLength] != 0 && !visitedIndex.contains(jumpLength)) {
                visitedIndex.add(jumpLength);
                queue.add(jumpLength);
                continue;
            }

            for (int i = jumpLength; i > index; i--) {
                if (visitedIndex.contains(i)) {
                    continue;
                }
                visitedIndex.add(i);
                queue.add(i);
            }
        }
        return false;
    }

    public static boolean canJump(int[] nums) {
        int N = nums.length;
        if (N == 1) {
            return true;
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] != 0) {
                continue;
            }
            boolean canJump = false;
            //  开始统计 i ... i-1 是否能跳过(i-1上的步数大于R-L的距离)当前屏障(0的位置)
            int R = i;
            int L = i - 1;
            //  不越界
            while (L >= 0) {
                //  当前障碍能跳过的两种可能:
                //  当R不是最后一个位置, 需要越过R, 当前L位置的步数 > (R 到 L 的距离)
                //  当R是最后一个位置, 需要越过R-1, 当前L位置的步数 >= (R 到 L 的距离)
                if ((nums[L] > R - L) || (R == N - 1 && nums[L] >= R - L)) {
                    canJump = true;
                    break;
                }
                L--;
            }

            if (!canJump) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 0, 4};
        System.out.println(canJump(nums));
        System.out.println(canJumpByBSF(nums));
    }
}