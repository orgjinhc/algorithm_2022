package leetcode.daily.easy;

/**
 * 有序数组
 * 指定L长度的序列
 * 返回数组内满足最大长度的元素数量
 */
public class MaxWeight {


    public static int process2(int[] arr, int targetNum) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int left = 0;
        int right = 1;
        int ans = Integer.MIN_VALUE;
        while (left < arr.length && right < arr.length) {
            //  制定最大窗口
            while (arr[right] - arr[left] > targetNum) {
                left++;
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;

    }

    public static int process1(int[] arr, int L) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i > 0; i--) {
            int mostLeftIndex = nextNearMostLeftIndex(arr, 0, i, arr[i] - L);
            ans = Math.max(ans, i - mostLeftIndex + 1);
        }
        return ans;
    }

    /**
     * 获取最左等于指定大小元素的位置
     *
     * @param arr
     * @param i
     * @return
     */
    private static int nextNearMostLeftIndex(int[] arr, int L, int R, int i) {
        int ans = Integer.MAX_VALUE;
        while (L < R) {
            int mid = L + ((R - L) / 2);
            if (arr[mid] < i) {
                L = mid + 1;
            } else if (arr[mid] == i) {
                ans = Math.min(ans, mid);
            }
            R = mid - 1;
        }
        return ans != Integer.MIN_VALUE ? L : ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 5, 6, 7};
        System.out.println(process1(arr, 1));
        System.out.println(process2(arr, 1));
    }
}
