package interview.sliding_window;


import java.util.LinkedList;

/**
 * 最值问题
 * 给定一个数组，指定一个边界内最大值组成一个数组返回
 */
public class MostValue {


    /**
     * 利用滑动窗口实现
     *
     * @param arr
     * @param num
     * @return
     */
    public static int[] process(int[] arr, int num) {
        //  condition
        if (null == arr || arr.length < 1) {
            return null;
        }

        int[] ans = new int[arr.length - num + 1];
        int index = 0;
        //  大->小 组织
        LinkedList<Integer> qMax = new LinkedList<>();
        for (int R = 0; R < arr.length; R++) {

            /**维护队列元素的增减**/
            //  根据双端队列组织形式定义数据存储结构
            while (!qMax.isEmpty() && arr[qMax.getLast()] < arr[R]) {
                qMax.pollLast();
            }

            //  将当前元素位置加入双端队列
            qMax.addLast(R);

            //  保持窗口大小维持固定长度
            if (qMax.peekFirst() == (R - num)) {
                qMax.pollFirst();
            }
            /**维护队列元素的增减**/


            /**维护返回数组元素的变化**/
            //  当前位置等于右边框，开始从双端队列里获取当前窗口内最大的下标放入ans
            //  保持当前双端队列内结构不变
            if (R >= num - 1) {
                ans[index++] = arr[qMax.peekFirst()];
            }
            /**维护返回数组元素的变化**/
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 1};
        int[] process = process(arr, 3);
        for (int i : process) {
            System.out.print(" " + i);
        }
    }
}
