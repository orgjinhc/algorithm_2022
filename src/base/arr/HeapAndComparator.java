package base.arr;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapAndComparator {

    public static void main(String[] args) {
        comparator1(new MyDescComparator());
        System.out.println();
        comparator2(new MyAscComparator());
    }

    private static void comparator1(MyDescComparator comparator) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(comparator);
        heap.add(3);
        heap.add(5);
        heap.add(2);
        heap.add(6);
        heap.add(1);
        heap.add(0);
        heap.add(9);

        while (!heap.isEmpty()) {
            System.out.print(heap.poll() + " ");
        }
    }

    private static void comparator2(MyAscComparator comparator) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(comparator);
        heap.add(3);
        heap.add(5);
        heap.add(2);
        heap.add(6);
        heap.add(1);
        heap.add(0);
        heap.add(9);

        while (!heap.isEmpty()) {
            System.out.print(heap.poll() + " ");
        }
    }

    static class MyDescComparator implements Comparator<Integer> {

        /**
         * 正数 param2 前
         * 负数 param1 前
         * 0 param1\param2 随便
         *
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    static class MyAscComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}
