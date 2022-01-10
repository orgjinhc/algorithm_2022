package leetcode.daily.everyday.january;

/**
 * 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 * <p>
 * 输入为三个整数：day、month 和year，分别表示日、月、年。
 * <p>
 * 您返回的结果必须是这几个值中的一个{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 * <p>
 * 示例 1：
 * <p>
 * 输入：day = 31, month = 8, year = 2019
 * 输出："Saturday"
 * 示例 2：
 * <p>
 * 输入：day = 18, month = 7, year = 1999
 * 输出："Sunday"
 * 示例 3：
 * <p>
 * 输入：day = 15, month = 8, year = 1993
 * 输出："Sunday"
 * <p>
 * 提示：
 * <p>
 * 给出的日期一定是在1971 到2100年之间的有效日期。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/day-of-the-week
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class January_3_DayOfTheWeek {

    static final String[] WEEK = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    static final int[] amount = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static String dayOfTheWeek(int day, int month, int year) {

        //  1971 ～ year 贡献
        int days = 365 * (year - 1 - 1970);
        //  1971 ~ year -1 之间, 存在闰年 days += 1
        for (int i = 1971; i < year; i++) {
            if ((i % 400 == 0 || (i % 4 == 0 && i % 100 != 0))) days += 1;
        }

        //  year年是否满足 闰年: 400倍数 || 4倍数 && 不是100倍数, 2月份多1天
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            ++amount[1];
        }

        //  计算1971 ～ year + month -1
        for (int i = 0; i < month - 1; ++i) {
            days += amount[i];
        }
        //  计算1971 ～ year + month + day
        days += day;

        //  由于1971年的第一天是星期5, 数组的第四位号位置
        days += 4 - 1;
        return WEEK[days % 7];
    }

    public static String dayOfTheWeek1(int day, int month, int year) {

        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};

        // 计算year-1年前的贡献
        int days = 365 * (year - 1 - 1970);
        // 加上baseline到year-1年前的闰年天数
        for (int i = 1971; i < year; i++) {
            if ((i % 400 == 0 || (i % 4 == 0 && i % 100 != 0))) days += 1;
        }
        // 计算year年是否是闰年
        if (month > 2 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) days += 1;

        // 计算year年month月 之前 的贡献
        for (int i = 0; i < month - 1; ++i)
            days += monthDays[i];

        // 计算year年month月的贡献，即day天
        days += day;
        return week[(days + 3) % 7];
    }

    public static void main(String[] args) {
        System.out.println(dayOfTheWeek(11, 7, 1983));
        System.out.println(dayOfTheWeek1(11, 7, 1983));

        System.out.println();

        System.out.println(dayOfTheWeek(18, 7, 1999));
        System.out.println(dayOfTheWeek1(18, 7, 1999));
    }
}