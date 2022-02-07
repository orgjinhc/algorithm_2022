package leetcode.everyday.january;

public class January_15_totalMoney {

    public static int totalMoney(int n) {
        int ans = 0;
        int weeks = n / 7;
        int lastWeekRemainingDay = n % 7;
        int startIndex = 1;
        while (weeks > 0) {
            for (int i = startIndex; i < 7 + startIndex; i++) {
                ans += i;
            }
            weeks--;
            startIndex++;
        }

        for (int i = startIndex; i < startIndex + lastWeekRemainingDay; i++) {
            ans += i;
        }
        return ans;
    }

    public static int totalMoney1(int n) {
        // 所有完整的周存的钱
        int weekNumber = n / 7;
        int firstWeekMoney = (1 + 7) * 7 / 2;
        int lastWeekMoney = firstWeekMoney + 7 * (weekNumber - 1);
        int weekMoney = (firstWeekMoney + lastWeekMoney) * weekNumber / 2;

        // 剩下的不能构成一个完整的周的天数里存的钱
        int dayNumber = n % 7;
        int firstDayMoney = 1 + weekNumber;
        int lastDayMoney = firstDayMoney + dayNumber - 1;
        int dayMoney = (firstDayMoney + lastDayMoney) * dayNumber / 2;
        return weekMoney + dayMoney;
    }

    public static void main(String[] args) {
        System.out.println(totalMoney(4));
        System.out.println(totalMoney(10));
        System.out.println(totalMoney(20));
    }
}
