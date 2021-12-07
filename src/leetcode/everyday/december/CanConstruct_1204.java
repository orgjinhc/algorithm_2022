package leetcode.everyday.december;

/**
 * 为了不在赎金信中暴露字迹，从杂志上搜索各个需要的字母，组成单词来表达意思。
 * <p>
 * 给你一个赎金信 (ransomNote) 字符串和一个杂志(magazine)字符串，判断 ransomNote 能不能由 magazines 里面的字符构成。
 * <p>
 * 如果可以构成，返回 true ；否则返回 false 。
 * <p>
 * magazine 中的每个字符只能在 ransomNote 中使用一次。
 * <p>
 * 链接：https://leetcode-cn.com/problems/ransom-note
 */
public class CanConstruct_1204 {


    /**
     * 词频方式解决
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null || ransomNote.length() > magazine.length()) {
            return false;
        }

        int[] ans = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            ans[magazine.charAt(i) - 'a'] ++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (ans[ransomNote.charAt(i) -'a']-- < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int a = 2;
        int b = Integer.MAX_VALUE;

        long max = 1;
        for (int i = 0; i < 200; i++) {
            max *= b;
        }
        System.out.println(max);
        System.out.println((429496729599L) % 1337);
    }
}
