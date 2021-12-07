import sun.security.provider.MD5;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MS {

    public static void process(int[] arr) {

    }


    public static int[][] ms(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        int[][] ans = new int[arr.length][2];
        Stack<List<Integer>> minToMax = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!minToMax.isEmpty() && arr[minToMax.peek().get(0)] > arr[i]) {
                List<Integer> pops = minToMax.pop();
                for (int L = 0; L < pops.size(); L++) {
                    int leftMinIndex = minToMax.isEmpty() ? -1 : minToMax.peek().get(pops.size() - 1);
                    ans[L][0] = leftMinIndex;
                    ans[L][1] = i;
                }
            }

            if (!minToMax.isEmpty() && arr[minToMax.peek().get(0)] == arr[i]) {
                minToMax.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                minToMax.push(list);
            }
        }

        while (!minToMax.isEmpty()) {
            List<Integer> pops = minToMax.pop();
            for (int i = 0; i < pops.size(); i++) {
                int leftMinIndex = minToMax.isEmpty() ? -1 : minToMax.peek().get(minToMax.peek().size() - 1);
                ans[pops.get(i)][0] = leftMinIndex;
                ans[pops.get(i)][1] = -1;
            }

        }
        return ans;
    }

    public static int[] tireSum(int[] arr) {
        int[] ans = new int[arr.length];
        ans[0] = arr[0];
        int index = 1;
        for (int i = 1; i < arr.length; i++) {
            ans[index] = arr[i] + ans[index - 1];
            index++;
        }
        return ans;
    }

    public static void main(String[] args) {

        String a1 = "aaaaaaaaaaaaaaaa1";
        String a2 = "aaaaaaaaaaaaaaaa2";
        String b1 = "b1";
        String b2 = "b2";
        String c1 = "c1";
        String c2 = "c2";
        System.out.println(a1.hashCode());
        System.out.println(a2.hashCode());
//        int[] arr = {1, 2, 3, 4};
//        //  单调栈 和 前缀和
//        int[][] ans = ms(arr);
//        int[] ansTireSum = tireSum(arr);
//
//        for (int i = 0; i < ans.length; i++) {
//            int L = ans[i][0];
//            int R = ans[i][1];
//            int sum = 0;
//            if (L == -1 && R == -1) {
//                sum = arr[i];
//            } else {
//                if (L == -1) {
//                    sum = ansTireSum[R - 1];
//                }
//                if (R == -1) {
//                    sum = ansTireSum[L + 1];
//                }
//                if (L != -1 && R != -1) {
//                    sum = ansTireSum[R - 1] - ansTireSum[L + 1];
//                }
//            }
//            System.out.println(arr[i] * sum);
//        }
    }
}
