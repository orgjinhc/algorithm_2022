package base.sort.no_compare;

public class CountSort {

    public static void main(String[] args) {
        int[] tmp = {21, 11, 13, 41, 17, 61, 19, 17, 81, 17};
        countSort(tmp);


        for (int i : tmp) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public static void countSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        //  获取数组内最大值, 用来定义目标数组（help数组）大小
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }


        //  定义目标数组
        int[] helpBucked = new int[++max];
        for (int i = 0; i < arr.length; i++) {
            //  取数组值放入桶相应位置, 并自增
            helpBucked[arr[i]]++;
        }


        int index = 0;
        //  倒回原数组
        for (int i = 0; i < helpBucked.length; i++) {
            while (helpBucked[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }
}
