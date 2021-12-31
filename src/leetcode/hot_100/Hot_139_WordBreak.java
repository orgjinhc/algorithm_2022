package leetcode.hot_100;

import java.util.*;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典，判定s 是否可以由空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：拆分时可以重复使用字典中的单词。
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-break
 */
public class Hot_139_WordBreak {

    /**
     * 输出所有的答案
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static List<String> wordBreakForAns(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        process(s, 0, ans, new ArrayList<>(), getDp(s, wordDict), buildTree(wordDict));
        return ans;
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

    /**
     * 暴力实现
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
     * 是否可以由 wordDict 构成
     * 通过前缀树 + dp实现
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreakByTrieTree(String s, List<String> wordDict) {
        boolean[] dp = getDp(s, wordDict);
        //  最后返回dp[0]位置得到的答案, 就是整个 s 是否在 wordDict 的答案
        return dp[0];
    }

    /**
     * 动态规划实现
     *
     * @param s
     * @param wordDict
     * @return
     */
    private static boolean[] getDp(String s, List<String> wordDict) {
        TrieTree root = buildTree(wordDict);

        int N = s.length();
        //  通过动态规划实现
        boolean[] dp = new boolean[N + 1];
        //  设置N位置为true, 含义是:N....以后的位置都是可以在字典里找到, ""字符默认匹配任意字符
        dp[N] = true;
        //  根据题意, 我们需要查看 s 是否可以在 wordDict 里找到, 需要从0位置开始一直找到s的尾部
        //  如果0位置在字典里, 就需要找 s 的 1...N-1 长度是否在字典 wordDict. 如果1位置在 wordDict, 就需要找 2...N-1 长度的字符是否在 wordDict.
        //  得出如下含义
        //  如果我们可以求出N-1位置是否在 wordDict, 那么 N-2 位置如果在 wordDict, 就不需要判断后续位置的字符是否在 wordDict, 大大加快寻找速度
        //  dp[] 的功能就是加速查找和帮助指定位置字符做判断

        //  开始逻辑
        for (int i = N - 1; i >= 0; i--) {
            //  每次都要获取trieTree用于判断指定位置是否在 wordDict
            TrieTree curTrieTree = root;

            //  判断当前位置到结束位置是否在 trieTree
            for (int end = i; end < N; end++) {
                TrieTree curNode = curTrieTree.nexts[s.charAt(end) - 'a'];
                //  当前字符不在前缀树内有节点, 直接跳出当前位置的判断, 直接进入下一个位置
                if (curNode == null) {
                    break;
                }
                //  当前位置在 trieTree, 查看是否是结束节点, 如果不是证明当前位置不在 wordDict
                //  如果是结束节点, 证明在 wordDict. 如果end位置还没有到 N-1 位置, 传统做法还需继续往后尝试, 看看后续 end+1 -> N-1 位置是否在 wordDict, 以证明i -> N-1 是否在 wordDict
                if (curNode.end) {
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
                //  跳到下一个节点
                curTrieTree = curNode;
            }
        }
        return dp;
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


    /**
     * 统计满足答案的 wordDict 集合
     *
     * @param s
     * @param index
     * @param ans
     * @param path
     * @param dp
     * @param trieTree
     */
    public static void process(String s, int index, List<String> ans, List<String> path, boolean[] dp, TrieTree trieTree) {
        int N = s.length();
        if (index == N) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                builder.append(path.get(i)).append(" ");
            }
            builder.append(path.get(path.size() - 1));
            ans.add(builder.toString());
        } else {
            TrieTree cur = trieTree;
            //  从 index 开始 往 字符串尾部开始寻找答案并依次添加到path里
            for (int end = index; end < N; end++) {
                //  每个位置位置是否在前缀树结构里有路径
                TrieTree curNode = cur.nexts[s.charAt(end) - 'a'];
                //  如果不存在路径, index 位置不用继续尝试了, 证明这个位置找不到答案, 直接返回
                if (curNode == null) {
                    break;
                }
                //  如果当前位置在前缀树上是一条完整的路径(是结束节点), 并且下一个节点也在 wordDict 内
                if (curNode.end && dp[end + 1]) {
                    //  添加现场
                    path.add(s.substring(index, end + 1));

                    //  继续寻找下一位置
                    process(s, end + 1, ans, path, dp, trieTree);

                    //  恢复现场
                    path.remove(path.size() - 1);
                }
                //  当前路径继续往下走
                cur = curNode;
            }
        }
    }


    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> strings = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> ans = wordBreakForAns(s, strings);
        for (String an : ans) {
            System.out.println(" " + an);
        }
        System.out.println();
    }
}
