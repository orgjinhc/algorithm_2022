package leetcode.everyday.december;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * <p>
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
 * 输出：true
 * 解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 * <p>
 * 输入：hand = [1,2,3,4,5], groupSize = 4
 * 输出：false
 * 解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
 * <p>
 * 提示：
 * <p>
 * 1 <= hand.length <= 104
 * 0 <= hand[i] <= 109
 * 1 <= groupSize <= hand.length
 * <p>
 * 链接：https://leetcode-cn.com/problems/hand-of-straights
 */
public class Daily_846_IsNStraightHand {

    public boolean isNStraightHand(int[] hand, int groupSize) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        //  记录词频
        Map<Integer, Integer> countSize = new HashMap<>();
        for (int num : hand) {
            countSize.put(num, countSize.getOrDefault(num, 0) + 1);
            queue.add(num);
        }

        //  顺子顺序集合不为空
        while (!queue.isEmpty()) {
            //  当前顺子的起始位置
            Integer L = queue.poll();
            //  如果顺子起始位置对应的词频已经为0, 就不去尝试了
            if (countSize.get(L) == 0) {
                continue;
            }

            //  构造顺子, 找到连续的一组数字, 更新词频表, 任何一个位置的数不存在, 返回false
            for (int start = 0; start < groupSize; start++) {
                Integer count = countSize.get(L + start);
                if (null == count || count == 0) {
                    return false;
                }
                countSize.put(L + start, count - 1);
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }
}