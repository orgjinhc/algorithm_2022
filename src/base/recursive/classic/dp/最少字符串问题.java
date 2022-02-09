package base.recursive.classic.dp;

/**
 * 范围模型
 */
public class 最少字符串问题 {

    public static void main(String[] args) {
        String[] str = {"ba", "c", "abcd"};
        System.out.println(process(str, "babac"));
    }

    public static int process(String[] str, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : str) {
            String rest = minus(first, target);
            if (rest.length() != target.length()) {
                min = Math.min(min, process(str, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String minus(String first, String target) {
        int[] count = new int[26];
        for (int i = 0; i < target.length(); i++) {
            count[target.charAt(i) - 'a']++;
        }

        for (int i = 0; i < first.length(); i++) {
            count[first.charAt(i) - 'a']--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            int rest = count[i];
            if (rest <= 0) {
                continue;
            }
            for (int j = 0; j < rest; j++) {
                sb.append((char) (i + 'a'));
            }
        }
        return sb.toString();
    }


}
