package leetcode.hot_100;

import java.util.*;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典，判定s 是否可以由空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：拆分时可以重复使用字典中的单词。
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-break
 */
public class WordBreak_139 {

    /**
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        return process(s, 0, 0, new HashSet<>(wordDict));
    }


    /**
     * 暴力实现
     * 处理L...R是否在wordDict内
     *
     * @param s
     * @param L
     * @param R
     * @param wordDict
     */
    public static boolean process(String s, int L, int R, Set<String> wordDict) {
        while (R < s.length()) {
            String substring = s.substring(L, R);
            //  如果L...R在wordDict内.继续判断 R...s.length是否在wordDict内, 如果字串不满足, 当前L...R继续扩大判断
            if (wordDict.contains(substring) && process(s, R, R + 1, wordDict)) {
                return true;
            }
            R++;
        }
        //  判断最后一次满足到字符串结尾这段距离是否满足
        return wordDict.contains(s.substring(L, R));
    }


    /**
     * 通过前缀树 + 暴力
     *  todo
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreakByBaoli(String s, List<String> wordDict) {
        TrieTree root = buildTree(wordDict);
        int N = s.length();
        for (int i = N - 1; i >= 0; i--) {
            TrieTree curTrieTree = root;
            for (int end = i; end < N; end++) {
                TrieTree cur = curTrieTree.nexts[s.charAt(end) - 'a'];
                if (cur == null) {
                    break;
                }
                if (cur.end) {
                    for (int j = end; j < N; j++) {

                    }
                }
                curTrieTree = cur;
            }
        }
        return false;
    }


    /**
     * 前缀树结构
     */
    public static class TrieTree {
        private boolean end;
        private TrieTree[] nexts;

        public TrieTree() {
            end = false;
            nexts = new TrieTree[26];
        }
    }

    /**
     * 通过前缀树 + dp实现
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreakByTrieTree(String s, List<String> wordDict) {
        TrieTree root = buildTree(wordDict);

        int N = s.length();
        //  通过动态规划实现
        boolean[] dp = new boolean[N + 1];
        //  设置N位置为true, 含义是:N....以后的位置都是满足题意, 就是可以在字典里找到, ""字符默认匹配任意字符
        dp[N] = true;
        //  根据题意, 我们需要查看 s 是否可以在 wordDict 里找到, 需要从0位置开始一直找到s的距离。
        //  如果0位置在字典里, 就需要找1...N-1长度是否在字典 wordDict. 如果1位置在 wordDict, 就需要找2...N-1长度的字符是否在 wordDict.
        //  得出如下含义
        //  如果我们可以求出N-1位置是否在 wordDict, 那么 N-2 位置如果在 wordDict, 就不需要判断后续位置的字符是否在 wordDict, 大大加快寻找速度
        //  dp[] 的功能就是加速查找和帮助指定位置字符做判断

        //  开始逻辑
        for (int i = N - 1; i >= 0; i--) {
            //  每次都要获取trieTree用于判断指定位置是否在 wordDict
            TrieTree curTrieTree = root;

            //  判断当前位置到结束位置是否在 trieTree
            for (int end = i; end < N; end++) {
                TrieTree cur = curTrieTree.nexts[s.charAt(end) - 'a'];
                //  当前字符不在前缀树内有节点, 直接跳出当前位置的判断, 直接进入下一个位置
                if (cur == null) {
                    break;
                }
                //  当前位置在 trieTree, 查看是否是结束节点, 如果不是证明当前位置不在 wordDict
                //  如果是结束节点, 证明在 wordDict. 如果end位置还没有到 N-1 位置, 传统做法还需继续往后尝试, 看看后续 end+1 -> N-1 位置是否在 wordDict, 以证明i -> N-1 是否在 wordDict
                if (cur.end) {
                    //  继续后面字符的递归或遍历
                    //  技巧
                    //  dp[i]的原因是:从 i 位置出发到 end 位置正好匹配到树的结束节点, 证明 i->end 所在位置的字符串在 wordDict
                    //  根据dp的语意得出, 当前i -> end已经得出答案了, end -> N-1 是否也能得到答案
                    dp[i] |= dp[end + 1];
                }
                //  通过上面的技巧(dp核心)可以快速判断后续是否继续满足
                //  i -> end 满足, end -> N-1 满足, i -> N-1 位置满足
                if (dp[i]) {
                    break;
                }
                //  跳到下一节点
                curTrieTree = cur;
            }
        }
        //  最后返回dp[0]位置得到的答案, 就是整个 s 是否在 wordDict 的答案
        return dp[0];
    }

    /**
     * 构建前缀树结构
     *
     * @param wordDict
     * @return
     */
    private static TrieTree buildTree(List<String> wordDict) {
        TrieTree root = new TrieTree();
        for (String word : wordDict) {
            TrieTree curRoot = root;
            for (int i = 0; i < word.length(); i++) {
                if (curRoot.nexts[word.charAt(i) - 'a'] == null) {
                    curRoot.nexts[word.charAt(i) - 'a'] = new TrieTree();
                }
                curRoot = curRoot.nexts[word.charAt(i) - 'a'];
            }
            curRoot.end = true;
        }
        return root;
    }


    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(wordBreakByTrieTree(s, Arrays.asList("leet", "cod")));
    }
}
