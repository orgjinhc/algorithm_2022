package leetcode.hot_100;

import leetcode.util.LCUtil;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 */
public class Hot_56_Merge_56 {

    /**
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * <p>
     * 输入：intervals = [[1,4],[4,5]]
     * 输出：[[1,5]]
     * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        int m = intervals.length;
        int[][] ans = new int[m][2];
        if (null == intervals || m == 0) {
            return ans;
        }
        if (m == 1) {
            return intervals;
        }
        //  先把数组根据start位置排序, 让小的排前面
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        //  解决思路, 记录当前 start 和 end, 判断下个位置 start 是否在当前位置 start 和 end 边界内, 如果在那就合并这俩个数组, 否则就找到了一组答案
        int index = 0;
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < m; i++) {
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];
            //  下个位置的开始大于当前位置的结束
            if (nextStart > end) {
                //  收集当前区间
                ans[index][0] = start;
                ans[index][1] = end;
                index++;
                //  更新区间开始和结束位置
                start = nextStart;
                end = nextEnd;
            } else {
                //  更新当前区间的结束位置
                end = Math.max(end, nextEnd);
            }
        }
        //  最后收集一下最后一个开始和结束区间
        ans[index][0] = start;
        ans[index][1] = end;
        index++;
        return generatedMatrix(ans, index);
    }

    private static int[][] generatedMatrix(int[][] ans, int index) {
        int[][] result = new int[index][2];
        for (int i = 0; i < ans.length; i++) {
            if (ans[i][0] != 0 && ans[i][1] != 0) {
                result[i][0] = ans[i][0];
                result[i][1] = ans[i][1];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 4}, {5, 6}};

        for (int[] an : merge(intervals)) {
            LCUtil.print(an);
        }
    }
}
