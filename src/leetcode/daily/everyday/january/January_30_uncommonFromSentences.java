package leetcode.daily.everyday.january;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class January_30_uncommonFromSentences {

    public String[] uncommonFromSentences(String s1, String s2) {
        final String SPACE = " ";
        HashMap<String, Integer> dp = new HashMap<>();
        String str = s1 + SPACE + s2;
        for (String s : str.split(SPACE)) {
            dp.put(s, dp.getOrDefault(s, 0) + 1);
        }


        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dp.entrySet()) {
            if (entry.getValue() == 1) {
                ans.add(entry.getKey());
            }
        }

        return ans.toArray(new String[0]);
    }
}