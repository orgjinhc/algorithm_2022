package base.sort.compare;

import leetcode.util.LCUtil;

public class MergeSort {

    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 6, 4, 8, 2, 9};
        mergeSort(nums, 0, nums.length - 1);
        LCUtil.print(nums);
    }


    /**
     * 非递归方式
     *
     * @param arr
     */
    public static void noProcess(int[] arr) {

        int N = arr.length;
        if (arr == null || N == 0 || N == 1) {
            return;
        }
        int step = 1;
        //  为什么不加上 = 条件, 如果等于就证明只有左组没有右组，这种情况说明整个数组已经有序，无需浪费一次排序
        while (step < N) {
            //  处理内部按照步长进行归并流程, 需要找到以步长划分的左右两组结构
            //  默认左组从0开始
            int L = 0;
            //  步长以遍历完整个数组为目标，结束条件就是左组下标越界
            while (L <= N) {

                //  计算mid，考虑边界问题[1,4,2,7,4] step=2 mid = 4 + 2 - 1 > 4
                int mid = 0;
                if (N - L >= step) {
                    mid = L + step - 1;
                } else {
                    mid = N - 1;
                }
                //  mid已经到达边界, 没有右组的情况, 直接下一次步长调整流程
                if (mid == N - 1) {
                    break;
                }
                //  L...mid


                //  mid + 1 ... R
                //  计算R位置，考虑边界问题 [1,5,3,2,0] step = 2 mid = 4
                int R = 0;
                if (N - 1 - mid >= step) {
                    R = mid + step;
                } else {
                    R = N - 1;
                }

                //  合并过程
                merge(arr, L, mid, R);

                //  如果R已经到边界了, 无需继续合并流程, 跳过当前步长
                if (R == N - 1) {
                    break;
                }
                //  计算下一个L的位置
                L = R + 1;
            }

            //  说明整个数组已经被划分为两部分，并且已经完成了合并
            //  = 情况有问题
            if (step > N >> 1) {
                break;
            } else {
                step = step << 1;
            }
            //  step = step << 1 有溢出风险
        }
    }


    /**
     * 递归方式
     *
     * @param arr
     * @param L
     * @param R
     */
    public static void mergeSort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        //  找到mid
        int findMid = (L + R) / 2;
        //  左边保证有序
        mergeSort(arr, L, findMid);
        //  右边保证有序
        mergeSort(arr, findMid + 1, R);
        //  合并两边, 保证全局有序
        merge(arr, L, findMid, R);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int p1 = left;
        int p2 = mid + 1;
        int i = 0;

        //  处理合并流程, 只要有一个指针越界, 退出当前合并流程
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        //  p2越界, 数组还有元素没有合并
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        //  p1越界, 数组还有元素没有合并
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }

        //  原数组排序
        for (int i1 = 0; i1 < help.length; i1++) {
            arr[left + i1] = help[i1];
        }
    }
}
