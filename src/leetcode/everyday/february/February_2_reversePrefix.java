package leetcode.everyday.february;

public class February_2_reversePrefix {

    public static String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index == -1) {
            return word;
        }

        String subWord = word.substring(0, index + 1);
        StringBuilder sb = new StringBuilder();
        sb.append(subWord);
        StringBuilder ans = sb.reverse();
        return ans + word.substring(index + 1);
    }

    public static void main(String[] args) {

    }
}
