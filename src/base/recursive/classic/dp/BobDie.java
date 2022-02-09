package base.recursive.classic.dp;

public class BobDie {

    public static int livePosibility(int row, int col, int rest, int n, int m) {
        if (row < 0 || col < 0 || row == n || col == m) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }

        int up = livePosibility(row + 1, col, rest - 1, n, m);
        int down = livePosibility(row - 1, col, rest - 1, n, m);
        int left = livePosibility(row, col - 1, rest - 1, n, m);
        int right = livePosibility(row, col + 1, rest - 1, n, m);
        return up + down + left + right;
    }

    public static void main(String[] args) {
        
    }
}
