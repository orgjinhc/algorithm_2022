package base.topic;

import java.util.LinkedHashMap;

public class PositionAndCount {


    static class Info {
        int val;
        Character ch;

        public Info() {
        }

        public Info(int val, Character index) {
            this.val = val;
            this.ch = index;
        }
    }

    public static Info process(String msg) {
        if (null == msg || msg.trim().length() < 1) {
            return new Info(0, null);
        }

        char[] chars = msg.toCharArray();

        int[] value_index = new int[msg.length()];
        int max = Integer.MIN_VALUE;
        Character character = null;
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + 1);
            } else {
                map.put(chars[i], 1);
            }

            if (map.get(chars[i]) >= max) {
                value_index[i] = max;
                character = chars[i];
                max = map.get(chars[i]);
            }
        }
        return new Info(max, character);
    }

    public static void main(String[] args) {
        String msg = "hello world every body";
        Info process = process(msg);
        System.out.println(process.val);
        System.out.println(process.ch);
    }
}
