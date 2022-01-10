package leetcode.daily.everyday.december;

import java.util.Arrays;

/**
 * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
 * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
 * <p>
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * 否则，x 将会向 y 发送一条好友请求。
 * <p>
 * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
 * <p>
 * 返回在该社交媒体网站上产生的好友请求总数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：ages = [16,16]
 * 输出：2
 * 解释：2 人互发好友请求。
 * 示例 2：
 * <p>
 * 输入：ages = [16,17,18]
 * 输出：2
 * 解释：产生的好友请求为 17 -> 16 ，18 -> 17 。
 * 示例 3：
 * <p>
 * 输入：ages = [20,30,100,110,120]
 * 输出：3
 * 解释：产生的好友请求为 110 -> 100 ，120 -> 110 ，120 -> 100 。
 * <p>
 * 提示：
 * <p>
 * n == ages.length
 * 1 <= n <= 2 * 104
 * 1 <= ages[i] <= 120
 * <p>
 * 链接：https://leetcode-cn.com/problems/friends-of-appropriate-ages
 */
public class Daily_825_NumFriendRequests {

    /**
     * age[y] <= 0.5 * age[x] + 7
     * age[y] > age[x]                      x >= y > 0.5x + 7
     * age[y] > 100 && age[x] < 100
     *
     * @param ages
     * @return
     */
    public static int numFriendRequests(int[] ages) {
        int n = ages.length;
        Arrays.sort(ages);
        int left = 0;
        int right = 0;
        int ans = 0;

        //  0.5 * age + 7 < 【 left ans right 】 <= age
        for (int age : ages) {
            //  找到满足的左边界
            while (!(ages[left] > 0.5 * age + 7)) {
                ++left;
            }
            //  找到满足的右边界
            while (right < n && ages[right] <= age) {
                ++right;
            }
            ans += right - left - 1;
        }
        return ans;
    }

    public static int numFriendRequests1(int[] ages) {
        int ans = 0;
        int N = ages.length;
        int L = 0;
        for (; L < N - 1; L++) {
            if (ages[L] == ages[L + 1]) {
                ans++;
                continue;
            }
            break;
        }

        //  y - x
        for (int R = N - 1; R > 0; R--) {
            for (int i = R; i > 0; i--) {
                if (!((ages[i - 1] <= (0.5 * ages[R] + 7)) || (ages[i - 1] > ages[R]) || (ages[i - 1] > 100 && ages[R] < 100)) || !((ages[R] <= (0.5 * ages[i - 1] + 7)) || (ages[R] > ages[i - 1]) || (ages[R] > 100 && ages[i - 1] < 100))) {
                    ans++;
                    continue;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ages = {20, 30, 100, 110, 120};
        System.out.println(numFriendRequests(ages));
    }
}