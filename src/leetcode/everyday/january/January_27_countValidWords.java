package leetcode.everyday.january;

public class January_27_countValidWords {

    public static int countValidWords(String sentence) {
        String[] subWords = sentence.split(" ");
        int ans = 0;
        for (String subWord : subWords) {
            if (validWords(subWord)) {
                ans++;
            }
        }
        return ans;
    }

    public static boolean validWords(String word) {
        int n = word.length();
        if (n == 0) {
            return false;
        }
        boolean hasHyphens = false;
        for (int i = 0; i < n; i++) {
            char curChar = word.charAt(i);
            if (Character.isDigit(curChar)) {
                return false;
            } else if (curChar == '-') {
                if (hasHyphens == true || i == 0 || i == n - 1 || !Character.isLetter(word.charAt(i - 1)) || !Character.isLetter(word.charAt(i + 1))) {
                    return false;
                }
                hasHyphens = true;
            } else if (curChar == '!' || curChar == '.' || curChar == ',') {
                if (i != n - 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }
}
