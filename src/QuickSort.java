public class QuickSort {


    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    /**
     * 简单方式实现
     *
     * @param arr
     */
    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int mid = patition1(arr, L, R);

        /**
         *  mid为边界条件，不能参与下次排序
         */
        process1(arr, L, mid - 1);
        process1(arr, mid + 1, R);
    }


    /**
     * 两个区域
     * <= R  >R
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int patition1(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] <= arr[R]) {
                swap(arr, ++less, index);
            }
            index++;
        }

        swap(arr, more, ++less);
        return less;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process2(arr, 0, arr.length - 1);
    }


    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }

        int[] patition = patition2(arr, L, R);
        process2(arr, L, patition[0] - 1);
        process2(arr, patition[1] + 1, R);
    }


    /**
     * 三个区域
     * <= R  ==R  >=R
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] patition2(int[] arr, int L, int R) {
        /**
         * 越界情况返回-1-1
         */
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] < arr[R]) {
                swap(arr, ++less, index++);
            } else if (arr[index] == arr[R]) {
                index++;
            } else {
                swap(arr, --more, index);
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }


    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L > R) {
            return;
        }

        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] patition = patition2(arr, L, R);
        process3(arr, L, patition[0] - 1);
        process3(arr, patition[1] + 1, R);
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 4, 1, 6, 4, 7, 5, 9, 4};
        quickSort3(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
