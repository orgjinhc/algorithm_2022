package leetcode.daily.everyday.january;

import leetcode.util.LCUtil;

import java.util.ArrayList;
import java.util.List;

public class January_14_kSmallestPairs {

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int M = nums1.length - 1;
        int N = nums2.length - 1;
        int left = nums1[0] + nums2[0];
        int right = nums1[M] + nums2[N];

        while (left < right) {
            int mid = (left + right - 1) / 2;
            //  nums1 位置 头开始
            int pos1 = 0;
            //  nums2 位置 尾开始
            int pos2 = N;
            int cnt = 0;

            while (pos1 < nums1.length && pos2 >= 0) {
                if (nums1[pos1] + nums2[pos2] > mid) {
                    pos2--;
                } else {
                    cnt += pos2 + 1;
                    pos1++;
                }
            }

            if (cnt >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        int pos1 = 0;
        int pos2 = N;
        int cnt = 0;

        while (pos1 < nums1.length && pos2 >= 0) {
            if (nums1[pos1] + nums2[pos2] >= right) {
                pos2--;
            } else {
                for (int i = 0; i <= pos2 && k > 0; i++, k--) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums1[pos1]);
                    cur.add(nums2[i]);
                    ans.add(cur);
                }
                pos1++;
            }
        }

        pos2 = N;

        for (int i = 0; i < nums1.length; i++) {
            while (pos2 > 0 && nums1[i] + nums2[pos2] > right) {
                pos2--;
            }
            for (int j = pos2; j >= 0 && (nums1[i] + nums2[j] == right) && k > 0; j--, k--) {
                List<Integer> cur = new ArrayList<>();
                cur.add(nums1[i]);
                cur.add(nums2[j]);
                ans.add(cur);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 2};
        int[] nums2 = {1, 2, 3};
        int k = 10;
        LCUtil.print(kSmallestPairs(nums1, nums2, k));
    }
}
