package leetcode.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game_1_divideString {
    public static String[] divideString(String s, int k, char fill) {
        int N = s.length();
        String[] ans = new String[N % k == 0 ? N / k : N / k + 1];
        int index = 0;
        for (int i = 0, j = 0; j < N; j++) {
            if (j % (k - 1) == 0 && j > 0) {
                StringBuilder sb = new StringBuilder();
                while (i <= j) {
                    sb.append(s.charAt(i++));
                }
                ans[index++] = sb.toString();
                i = j + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        String s = "abcdefghi";
//        int k = 3;
//        char fill = 'x';
//
//        String[] strings = divideString(s, k, fill);
//        for (String string : strings) {
//            System.out.println(string);
//        }

        Game_1_divideString game_1_divideString = new Game_1_divideString();
        game_1_divideString.update(1, 10);
        game_1_divideString.update(2, 5);
        System.out.println(game_1_divideString.current());
        System.out.println(game_1_divideString.maximum());
        game_1_divideString.update(1, 3);
        System.out.println(game_1_divideString.maximum());
    }

    HashMap<Integer, Integer> container;
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    public Game_1_divideString() {
        container = new HashMap();
    }

    public void update(int timestamp, int price) {
        if (container.containsKey(timestamp)) {
            Integer cur = container.get(timestamp);
            if (cur == null) {
                min = Math.min(min, price);
                max = Math.max(max, price);
            } else {
                max = Math.max(min, price);
                min = Math.min(min, price);
            }
        } else {
            min = Math.min(min, price);
            max = Math.max(max, price);
        }
        container.put(timestamp, price);
    }

    public int current() {
        return container.get(container.size() - 1);
    }

    public int maximum() {
        return max;
    }

    public int minimum() {
        return min;
    }
}
