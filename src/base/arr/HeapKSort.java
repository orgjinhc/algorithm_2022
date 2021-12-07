package base.arr;

import java.util.PriorityQueue;

public class HeapKSort {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 11, 13, 14, 5, 6, 7,33,44,55};
        process(arr, 3);

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] process(int[] arr, int k) {
        if (null == arr || arr.length < 2) {
            return arr;
        }

        //  构建小根堆, 保证前k个数是有序的
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < Math.min(k, arr.length); i++) {
            heap.add(arr[i]);
        }

        //  从堆内获取元素, 添加到arr内, 堆内数据保证有序
        int index = 0;
        for (int i = k; i < arr.length; i++) {
            heap.add(arr[i]);
            arr[index++] = heap.poll();
        }

        //  继续出队
        while (!heap.isEmpty()) {
            arr[index++] = heap.poll();
        }
        return arr;
    }

}