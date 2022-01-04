package leetcode.daily.medium;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.PriorityQueue;

public class Daily_295_MedianFinder {

    /**
     * 大根堆
     * 4->3->2->1
     */
    private static PriorityQueue<Integer> max = null;

    /**
     * 小根堆
     * 1->2->3->4
     */
    private static PriorityQueue<Integer> min = null;

    public Daily_295_MedianFinder() {
        max = new PriorityQueue<>((o1, o2) -> o1 - o2);
        min = new PriorityQueue<>((o1, o2) -> o2 - o1);
    }

    public void addNum(int num) {
        if (max.isEmpty() || max.peek() >= num) {
            max.add(num);
        } else {
            min.add(num);
        }
        balance();
    }

    public double findMedian() {
        if (max.size() == min.size()) {
            return (max.peek() + min.peek()) / 2;
        }
        return max.size() > min.size() ? max.peek() : min.peek();
    }

    public static void balance() {
        if (Math.abs(max.size() - min.size()) == 2) {
            if (min.size() > max.size()) {
                max.add(min.poll());
            } else {
                min.add(max.poll());
            }
        }
    }
}
