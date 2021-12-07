package base.arr;

/**
 * 小根堆[3,5,2,6,1,0,9]
 * 构建方式:
 * 一次一个数构建
 * 一次一堆数[一个数组]构建
 */
public class MinHeap {

    private int[] heap;

    private int heapSize = 0;

    private int limit = 10;

    public MinHeap(int heapSize, int limit) {
        heap = new int[heapSize];
        this.limit = limit;
    }

    public int size() {
        return heapSize;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean add(Integer value) {
        if (heapSize > limit) {
            throw new RuntimeException("max limit");
        }

        heap[heapSize] = value;
        heapInsert(heapSize++);
        return true;
    }

    public Integer poll() {
        if (heapSize < 1) {
            throw new RuntimeException("empty");
        }

        int ans = heap[0];
        swap(0, --heapSize);
        heapify(0, heapSize);
        return ans;
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
    private void heapify(int index, int heapSize) {
        int left = index * 2 + 1;

        while (heapSize > left) {
            int right = left + 1;
            //  获取左右孩子最小孩子的位置
            int least;
            if (heapSize > right) {
                if (heap[left] > heap[right]) {
                    least = right;
                } else {
                    least = left;
                }
            } else {
                least = left;
            }

            //  父子比较 父大于最小 最小
            least = heap[least] < heap[index] ? least : index;

            //  子不大于父, 无需调整
            if (least == index) {
                return;
            }

            //  父子交换位置
            swap(least, index);

            //  父来到小儿子位置
            index = least;

            //  继续向下找左儿子
            left = index * 2 + 1;
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
    private void heapInsert(int index) {
        //  index 当前节点
        //  父 (index - 1) / 2
        while (heap[index] < heap[(index - 1) / 2]) {
            //  交换
            swap(index, (index - 1) / 2);
            //  递归
            index = (index - 1) / 2;
        }
    }

    /**
     * 交换方式
     * 1.普通交换
     * 2.^交换
     *
     * @param index
     * @param parentIndex
     */
    private void swap(int index, int parentIndex) {
        int tmp = heap[index];
        heap[index] = heap[parentIndex];
        heap[parentIndex] = tmp;
    }


    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10, 10);
        heap.add(3);
        heap.add(5);
        heap.add(2);
        heap.add(2);
        heap.add(6);
        heap.add(1);
        heap.add(1);
        heap.add(0);
        heap.add(9);

        while (!heap.isEmpty()) {
            System.out.print(heap.poll() + " ");
        }

    }

}