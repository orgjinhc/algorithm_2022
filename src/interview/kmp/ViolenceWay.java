package interview.kmp;

/**
 * String str = "aabaacaabaats";
 * String match = "aabaat";
 */
public class ViolenceWay {

    public static int process(String str, String match) {
        if (null == str || null == match || str.length() < 1 || match.length() < 1) {
            return -1;
        }

        if (match.length() > str.length()) {
            return -1;
        }

        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();

        int ans = -1;
        int index = 0;
        for (int L = 0; L < strChars.length; L++) {
            for (int i = L; i < strChars.length; i++) {
                if (matchChars[index] != strChars[i]) {
                    index = 0;
                    break;
                }
                if (++index == matchChars.length) {
                    ans = L;
                    break;
                }
            }
            if (ans != -1) {
                break;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String str = "abdabccbc";
        String match = "cbc";

        System.out.println(process(str, match));
    }
}
