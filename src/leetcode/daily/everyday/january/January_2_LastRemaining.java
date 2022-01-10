package leetcode.daily.everyday.january;

import java.util.ArrayList;
import java.util.List;

public class January_2_LastRemaining {

    public static int lastRemaining(int n) {
        int head = 1;
        int step = 1;
        boolean leftRemove = true;
        while (n > 1) {
            //  头节点更新规律: 从左边开始移除 or（从右边开始移除，数列总数为奇数）
            if (leftRemove || n % 2 != 0) {
                head += step;
            }
            //  步长变更规律 * 2
            step *= 2;
            //  取反移除方向
            leftRemove = !leftRemove;
            //  总数 / 2
            n /= 2;
        }
        return head;
    }

    public static int lastRemaining1(int n) {
        if (n == 1) {
            return 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            ans.add(i);
        }
        while (true) {
            ans = remainingByLeft(ans);
            if (ans.size() == 1) {
                break;
            }
            ans = remainingByRight(ans);
            if (ans.size() == 1) {
                break;
            }
        }
        return ans.get(0);
    }

    public static List<Integer> remainingByLeft(List<Integer> list) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < list.size(); i += 2) {
            ans.add(list.get(i));
        }
        return ans;
    }

    public static List<Integer> remainingByRight(List<Integer> list) {
        List<Integer> ans = new ArrayList<>();
        for (int i = list.size() - 2; i >= 0; i -= 2) {
            ans.add(list.get(i));
        }
        revert(ans);
        return ans;
    }

    public static void revert(List<Integer> list) {
        int L = 0;
        int R = list.size() - 1;
        while (L <= R) {
            Integer LNum = list.get(L);
            Integer RNum = list.get(R);
            list.set(L, RNum);
            list.set(R, LNum);
            L++;
            R--;
        }
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(9));
    }
}
