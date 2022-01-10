package leetcode.daily.everyday.december;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Daily_507_CheckPerfectNumber {

    /**
     * 枚举所有可能情况
     *
     * @param num
     * @return
     */
    public static boolean checkPerfectNumber1(int num) {
        if (num == 1) {
            return false;
        }
        //  1默认作为因子
        int sum = 1;
        //  从2开始枚举
        for (int d = 2; d * d <= num; ++d) {
            //  能被枚举数除尽, 证明当前枚举数是因子, 添加到结果中
            if (num % d == 0) {
                sum += d;
                //  计算因子平方是否小于 num, 满足条件的 num/d 也满足条件
                if (d * d < num) {
                    sum += num / d;
                }
            }
        }
        return sum == num;
    }

    /**
     * 枚举所有可能情况
     *
     * @param num
     * @return
     */
    public static boolean checkPerfectNumber2(int num) {
        if (num == 1) {
            return false;
        }
        Set<Integer> ans = new HashSet<>();
        ans.add(1);
        for (int d = 2; d < num; ++d) {
            if (num % d == 0) {
                ans.add(d);
            }
        }

        int sum = 0;
        for (Integer an : ans) {
            sum += an;
        }
        return sum == num;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 33550336; i++) {
                if (checkPerfectNumber1(i)) {
                    System.out.println(Thread.currentThread().getName() + "   " + i);
                }
            }
            latch.countDown();
        });
        thread.setName("线程1");
        thread.start();
        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 33550336; i++) {
                if (checkPerfectNumber1(i)) {
                    System.out.println(Thread.currentThread().getName() + "   " + i);
                }
            }
            latch.countDown();
        });
        thread2.setName("线程2");
        thread2.start();
        latch.await();
    }
}