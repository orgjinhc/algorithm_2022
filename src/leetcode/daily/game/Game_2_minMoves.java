package leetcode.daily.game;

public class Game_2_minMoves {

    public static int minMoves(int target, int maxDoubles) {
        int ans = 0;
        while (target != 1) {
            if (target % 2 == 0 && maxDoubles > 0) {
                maxDoubles--;
                target = target / 2;
            } else if (maxDoubles == 0) {
                ans += target - 1;
                return ans;
            } else {
                target--;
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        //  1000000000
        System.out.println(minMoves(1000000000, 0));
    }
}
