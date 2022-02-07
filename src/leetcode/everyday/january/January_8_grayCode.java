package leetcode.everyday.january;

import java.util.ArrayList;
import java.util.List;

public class January_8_grayCode {

    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            ret.add((i >> 1) ^ i);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(5 >> 1);
    }

}
