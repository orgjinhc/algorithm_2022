package leetcode.daily.everyday.january;

public class January_9_slowestKey {

    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        int maxTime = releaseTimes[0];
        char ans = keysPressed.charAt(0);
        for (int i = 1; i < releaseTimes.length; i++) {
            char curChar = keysPressed.charAt(i);
            int curTime = releaseTimes[i] - releaseTimes[i - 1];
            if (maxTime < curTime || (maxTime == curTime && ans < curChar)) {
                maxTime = curTime;
                ans = curChar;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] releaseTimes = {12, 23, 36, 46, 62};
        String keysPressed = "spuda";
        System.out.println(slowestKey(releaseTimes, keysPressed));
    }
}