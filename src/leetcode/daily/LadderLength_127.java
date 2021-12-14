package leetcode.daily;

import java.util.*;

/**
 * 字典wordList 中从单词 beginWord和 endWord 的 转换序列 是一个按下述规格形成的序列：
 * <p>
 * 序列中第一个单词是 beginWord 。
 * 序列中最后一个单词是 endWord 。
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典wordList 中的单词。
 * 给你两个单词 beginWord和 endWord 和一个字典 wordList ，找到从beginWord 到endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。
 * <p>
 * 链接：https://leetcode-cn.com/problems/word-ladder
 */
public class LadderLength_127 {

    /**
     * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * 输出：5
     * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //   先将开始单词加入列表, 应为要从当前单词开始进行搜索
        wordList.add(beginWord);

        //  key : 列表中的单词，每一个单词都会有记录
        //  value : key这个单词，有哪些邻居(任何一个位置不同都算是邻居)
        Map<String, List<String>> nexts = getNexts(wordList);

        //  单词距离map
        HashMap<String, Integer> distanceMap = new HashMap<>();
        distanceMap.put(beginWord, 1);

        //  已经访问过的单词集合
        HashSet<String> visitsWordSet = new HashSet<>();
        visitsWordSet.add(beginWord);

        //  BFS
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        while (!queue.isEmpty()) {
            //  需要验证的单词
            String curWord = queue.poll();
            //  获取当前需要验证单词的所有邻居单词
            List<String> allNeighborsWords = nexts.get(curWord);
            //  遍历所有邻居查看是否有endWord存在
            for (String nextWord : allNeighborsWords) {

                //  当前邻居内有endWords, 直接放回当前word统计的距离并加上1距离就是答案
                if (nextWord.equals(endWord)) {
                    return distanceMap.get(curWord) + 1;
                }

                //  当前这个邻居词已经访问过了, 无需进行访问
                //  当前邻居词没有访问过, 将当前邻居词加入访问集合, 同时将邻居词加入待访问队列, 还需更新当前单词距离startWord的距离信息, 便于后续如果找到答案就可直接返回
                if (!visitsWordSet.contains(nextWord)) {
                    visitsWordSet.add(nextWord);
                    queue.add(nextWord);
                    distanceMap.put(nextWord, distanceMap.get(curWord) + 1);
                }
            }
        }
        return 0;
    }

    /**
     * 构造当前单词集合所有邻居词结构
     *
     * @param words 所有单词集合
     * @return
     */
    public static Map<String, List<String>> getNexts(List<String> words) {
        Set<String> dict = new HashSet<>(words);
        Map<String, List<String>> nexts = new HashMap<>();
        for (String word : words) {
            //  当前词和其所有邻居词集合
            nexts.put(word, getNeighborsWords(word, dict));
        }
        return nexts;
    }

    /**
     * 应该根据具体数据状况决定用什么来找邻居
     * 1)如果字符串长度比较短，字符串数量比较多，以下方法适合
     * 2)如果字符串长度比较长，字符串数量比较少，以下方法不适合
     * 邻居词定义
     * abc -> bbc,aac,abb 只有一位不相同的单词都是邻居词定义
     *
     * @param word
     * @param dict
     * @return
     */
    public static List<String> getNeighborsWords(String word, Set<String> dict) {
        List<String> neighborsWords = new ArrayList<>();
        char[] chs = word.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            for (char cur = 'a'; cur <= 'z'; cur++) {
                //  当前位置字符和原位置字符相同, 非邻居词定义  abc -> abc
                if (chs[i] == cur) {
                    continue;
                }
                char tmp = chs[i];
                chs[i] = cur;
                if (dict.contains(String.valueOf(chs))) {
                    neighborsWords.add(String.valueOf(chs));
                }
                chs[i] = tmp;
            }
        }
        return neighborsWords;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(ladderLength(beginWord, endWord, wordList));
    }
}
