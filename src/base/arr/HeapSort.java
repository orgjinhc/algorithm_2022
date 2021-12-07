package base.arr;

public class HeapSort {


    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 11, 13, 14, 5, 6, 7, 33, 44, 55, 9, 8, 0};
        sort(arr);

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public static void sort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

//        for (int i = 0; i < base.arr.length; i++) {
//            heapInsert(base.arr, i);
//        }

        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        int size = arr.length - 1;
        //  大根堆保证最大值来到数组尾部，小根堆保证最小值来到数组尾部
        swap(arr, 0, size);

        //  保证数组里的数都遍历一遍
        while (size > 0) {
            //  保证数组在size-1的长度上，还是堆结构
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }

    }


    /**
     * 查找逻辑：子找父
     * <p>
     * 构建方式：
     * 1.从上向下构建 复杂度 NlogN
     * 2.从下向上构建 复杂度 N
     * <p>
     * 算法逻辑：从下向上比较节点
     * 跟自己的父节点比较, 比父大就替换
     * <p>
     * 终止条件：
     * 1.无父节点
     * 2.没有父节点大
     *
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        //  index 当前节点
        //  父 (index - 1) / 2
        while (arr[index] > arr[(index - 1) / 2]) {
            //  交换
            swap(arr, index, (index - 1) / 2);
            //  递归
            index = (index - 1) / 2;
        }
    }

    /**
     * 查找逻辑：父找子
     * <p>
     * 构建结构
     * 从上向下
     * 算法逻辑:父找子
     *
     * @param index
     * @param heapSize
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;

        while (heapSize > left) {
            int right = left + 1;
            //  获取左右孩子最大孩子的位置
            int largest = heapSize > right && arr[right] > arr[left] ? right : left;

            //  父子比较
            largest = arr[largest] > arr[index] ? largest : index;

            //  子不大于父, 无需调整
            if (largest == index) {
                return;
            }

            //  父子交换位置
            swap(arr, largest, index);

            //  父来到大儿子位置
            index = largest;

            //  继续向下找左儿子
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int index, int parentIndex) {
        int tmp = arr[index];
        arr[index] = arr[parentIndex];
        arr[parentIndex] = tmp;
    }
}