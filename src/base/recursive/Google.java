package base.recursive;

public class Google {

    public static int process(char[] chars, int index) {
        if (index == chars.length) {
            return 1;
        }

        if (chars[index] == '0') {
            return 0;
        }

        if (chars[index] == '1') {
            int ans = process(chars, index + 1);
            if (index + 1 < chars.length) {
                ans += process(chars, index + 2);
            }
            return ans;
        }


        if (chars[index] == '2') {
            int ans = process(chars, index + 1);
            if (index + 1 < chars.length && (chars[index] >= '0' && chars[index] <= '6')) {
                ans += process(chars, index + 2);
            }
            return ans;
        }

        return process(chars, index + 1);
    }

    public static void main(String[] args) {
        String msg = "111";
        System.out.println(process(msg.toCharArray(), 0));
    }
}
