package module;

public class KmpMatch {

    public static int kmp(String str, String match) {
        if (null == str || null == match
                || str.length() != match.length()
                || str.length() < match.length()) {
            return -1;
        }
        str += str;
        char[] sourceChars = str.toCharArray();
        char[] targetChars = match.toCharArray();
        int[] nextArray = getNextArray(targetChars);
        int x = 0;
        int y = 0;
        while (y < targetChars.length && x < sourceChars.length) {
            if (sourceChars[x] == targetChars[y]) {
                x++;
                y++;
            } else if (y == 0) {
                x++;
            } else {
                y = nextArray[y];
            }
        }
        return y == targetChars.length ? x - y : -1;
    }

    /**
     * nextArray构造方式
     *
     * @param targetChars
     * @return
     */
    private static int[] getNextArray(char[] targetChars) {
        if (targetChars.length == 0) {
            return new int[]{-1};
        }

        if (targetChars.length == 1) {
            return new int[]{-1, 0};
        }

        int[] nextArray = new int[targetChars.length];
        nextArray[0] = -1;
        nextArray[1] = 0;

        int cn = 0;
        int i = 2;

        while (i < targetChars.length) {
            if (targetChars[i - 1] == targetChars[cn]) {
                nextArray[i++] = ++cn;
            } else if (cn > 0) {
                cn = nextArray[cn];
            } else {
                nextArray[i++] = 0;
            }
        }
        return nextArray;
    }

    /**
     * 外层是源数组，如果没有匹配成功，开始位置++
     * 内层是目标数组，每次必须从0开始匹配
     *
     * @param str
     * @param match
     * @return
     */
    public static boolean process1(String str, String match) {
        if (null == str || null == match
                || str.length() != match.length()
                || str.length() < match.length()) {
            return false;
        }
        str = str + str;
        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();

        int sLength = strChars.length;
        int mLength = matchChars.length;

        boolean matchFlag = false;
        int L = 0;
        while (!matchFlag) {
            if (sLength - L + 1 < mLength) {
                break;
            }
            for (int l = 0; l < mLength; l++) {
                if (strChars[L++] != matchChars[l]) {
                    matchFlag = false;
                    break;
                }
                matchFlag = true;
            }
        }
        return matchFlag;

    }

    /**
     * @param str
     * @param match
     */
    public static boolean process(String str, String match) {
        if (null == str || null == match || str.length() != match.length()) {
            return false;
        }

        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();

        //  边界条件
        int sourceBreakCondition = strChars.length;
        int matchBreakCondition = matchChars.length;
        //  目标数组开始位置
        int targetArrayStartIndex = 0;
        //  匹配数量
        int matchSize = 0;
        while (sourceBreakCondition > 0) {
            //  位置被复用，临时存储当前位置
            int tmp = targetArrayStartIndex;

            //  源数组每次都是从0开始匹配目标数组
            for (int i = 0; i < strChars.length; i++) {
                if (strChars[i] != matchChars[tmp++]) {
                    matchSize = 0;
                    break;
                }

                if (tmp == matchBreakCondition) {
                    tmp = 0;
                }
                matchSize++;
                continue;
            }

            if (matchSize == matchBreakCondition) {
                break;
            }
            targetArrayStartIndex++;
            sourceBreakCondition--;
        }
        return matchSize == strChars.length;
    }

    public static void main(String[] args) {
        String source = "3271456";
        String target = "71";
        System.out.println(kmp(source, target));
    }
}
