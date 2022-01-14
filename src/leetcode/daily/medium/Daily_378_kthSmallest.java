package leetcode.daily.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Daily_378_kthSmallest {

    public static int kthSmallest(int[][] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length;
        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
        boolean[][] dp = new boolean[N][M];
        heap.add(new Node(matrix[0][0], 0, 0));
        dp[0][0] = true;
        Node ans = null;
        while (!heap.isEmpty()) {
            ans = heap.poll();
            if (k-- <= 1) {
                break;
            }
            int row = ans.row;
            int col = ans.col;
            if (row + 1 < N && !dp[row + 1][col]) {
                heap.add(new Node(matrix[row + 1][col], row + 1, col));
                dp[row + 1][col] = true;
            }

            if (col + 1 < M && !dp[row][col + 1]) {
                heap.add(new Node(matrix[row][col + 1], row, col + 1));
                dp[row][col + 1] = true;
            }
        }
        return ans.value;
    }

    public static class Node {
        public int value;
        public int row;
        public int col;

        public Node(int v, int r, int c) {
            value = v;
            row = r;
            col = c;
        }
    }

    public static int kthSmallest2(int[][] matrix, int k) {
        int N = matrix.length;
        int M = matrix[0].length;

        int left = matrix[0][0];
        int right = matrix[N - 1][M - 1];

        int ans = 0;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // <=mid 有几个 <= mid 在矩阵中真实出现的数，谁最接近mid
            Info info = noMoreNum(matrix, mid);
            if (info.num < k) {
                left = mid + 1;
            } else {
                ans = info.near;
                right = mid - 1;
            }
        }
        return ans;
    }


    public static Info noMoreNum(int[][] matrix, int value) {
        int near = Integer.MIN_VALUE;
        int num = 0;
        int N = matrix.length;
        int M = matrix[0].length;

        int row = 0;
        int col = M - 1;

        while (row < N && col >= 0) {
            if (matrix[row][col] <= value) {
                near = Math.max(near, matrix[row][col]);
                num += col + 1;
                row++;
            } else {
                col--;
            }
        }
        return new Info(near, num);
    }

    public static class Info {
        public int near;
        public int num;

        public Info(int n1, int n2) {
            near = n1;
            num = n2;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        System.out.println(kthSmallest2(matrix, 8));
    }
}
