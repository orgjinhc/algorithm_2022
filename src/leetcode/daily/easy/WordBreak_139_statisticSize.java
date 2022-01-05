package leetcode.daily.easy;

import java.util.*;

/**
 * 统计所有条件的答案数量
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-break
 */
public class WordBreak_139_statisticSize {

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
     * 获取 s 通过 wordDict 可以得出多少种答案
     * 通过前缀树 + dp实现
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static int wordBreakSizeByTrieTree(String s, List<String> wordDict) {
        TrieTree root = buildTree(wordDict);

        int N = s.length();
        //  通过动态规划实现
        int[] dp = new int[N + 1];
        //  设置N位置为true, 含义是:N....以后的位置都是满足题意, 就是可以在字典里找到, ""字符默认匹配任意字符
        dp[N] = 1;
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
                    dp[i] += dp[end + 1];
                }
                curTrieTree = cur;
            }
        }
        //  最后返回dp[0]位置得到的答案, 就是整个 s 是否在 wordDict 的答案
        return dp[0];
    }


    public static void main(String[] args) {
        String s = "catsanddog";
        System.out.println(wordBreakSizeByTrieTree(s, Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }
}
