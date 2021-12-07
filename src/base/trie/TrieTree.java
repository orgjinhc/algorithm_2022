package base.trie;

import java.util.HashMap;

/**
 * 前缀树
 * 时间复杂度为字符数量
 */
public class TrieTree {

    /**
     * 无法穷举或种类很多：用hash表
     */
    static class Node2 {
        int pass;
        int end;
        HashMap<Integer, Node2> nexts;

        public Node2() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new HashMap<>();
        }
    }

    /**
     * 限制问题:字符串这种可以穷举的类型
     */
    static class Node {
        int pass;
        int end;
        Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;

            //  0 ~ 25 = 26个字母
            //  nexts[a] != null
            //  nexts[b] == null
            nexts = new Node[32];
        }
    }


    static class Trie {
        Node head;

        public Trie() {
            head = new Node();
        }


        /**
         * 插入逻辑
         *
         * @param word
         */
        public void insert(String word) {
            if (null == word || word.trim().length() < 1) {
                return;
            }

            //  查看当前添加路径
            char[] chars = word.toCharArray();
            Node node = head;
            node.pass++;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                Node next = node.nexts[path];
                if (next == null) {
                    next = new Node();
                }
                node = next;
                node.pass++;
            }
            node.end++;
        }

        /**
         * 删除逻辑
         * 1.先搜索当前字符串
         * 2.满足再删除
         *
         * @param word
         */
        public void delete(String word) {
            if (null == word || word.trim().length() < 1) {
                return;
            }

            if (search(word) != 0) {
                //  查看path
                char[] chars = word.toCharArray();
                //  所有节点都是从头出发, 头节点赋值给root 箭头在head
                Node node = head;
                //  箭头位置--
                node.pass--;
                for (int i = 0; i < chars.length; i++) {
                    node = node.nexts[chars[i] - 'a'];
                    //  箭头位置--
                    node.pass--;
                    if (node.pass == 0) {
                        return;
                    }
                }
                node.end--;
            }
        }

        /**
         * 字符串前缀
         *
         * @param word
         * @return
         */
        public int prefixNumber(String word) {
            if (null == word || word.trim().length() < 1) {
                return 0;
            }

            //  查看当前添加路径
            char[] chars = word.toCharArray();
            //  所有节点都是从头出发, 头节点赋值给root
            Node node = head;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                Node next = node.nexts[path];
                if (next == null) {
                    return 0;
                }
                //  跳转到下一节点
                node = next;
            }
            return node.pass;
        }


        /**
         * 搜索逻辑
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if (null == word || word.trim().length() < 1) {
                return 0;
            }

            //  查看当前添加路径
            char[] chars = word.toCharArray();
            //  所有节点都是从头出发, 头节点赋值给root
            Node node = head;

            for (int i = 0; i < chars.length; i++) {
                int path = chars[i] - 'a';
                Node next = node.nexts[path];
                if (next == null) {
                    return 0;
                }
                //  跳转到下一节点
                node = next;
            }
            return node.end;
        }
    }

    /**
     * GeoHash 前缀树
     */
    static class Trie2 {
        Node2 head;

        public Trie2() {
            head = new Node2();
        }

        /**
         * 插入逻辑
         *
         * @param word
         */
        public void insert(String word) {
            if (null == word || word.trim().length() < 1) {
                return;
            }

            char[] chars = word.toCharArray();
            Node2 node = head;
            node.pass++;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i];
                Node2 next = node.nexts.get(path);
                if (next == null) {
                    Node2 newNode = new Node2();
                    node.nexts.put(path, newNode);
                    node = newNode;
                } else {
                    node = next;
                }
                node.pass++;
            }
            node.end++;
        }

        /**
         * 删除逻辑
         * 1.先搜索当前字符串
         * 2.满足再删除
         *
         * @param word
         */
        public void delete(String word) {
            if (null == word || word.trim().length() < 1) {
                return;
            }

            if (search(word) != 0) {
                //  查看path
                char[] chars = word.toCharArray();
                //  所有节点都是从头出发, 头节点赋值给root 箭头在head
                Node2 node = head;
                //  箭头位置--
                node.pass--;
                for (int i = 0; i < chars.length; i++) {
                    node = node.nexts.get(chars[i]);
                    //  箭头位置--
                    node.pass--;
                    if (node.pass == 0) {
                        return;
                    }
                }
                node.end--;
            }
        }

        /**
         * 字符串前缀
         *
         * @param word
         * @return
         */
        public int prefixNumber(String word) {
            if (null == word || word.trim().length() < 1) {
                return 0;
            }

            //  查看当前添加路径
            char[] chars = word.toCharArray();
            //  所有节点都是从头出发, 头节点赋值给root
            Node2 node = head;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i];
                Node2 next = node.nexts.get(path);
                if (next == null) {
                    return 0;
                }
                //  跳转到下一节点
                node = next;
            }
            return node.pass;
        }


        /**
         * 搜索逻辑
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if (null == word || word.trim().length() < 1) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 node = head;
            for (int i = 0; i < chars.length; i++) {
                int path = chars[i];
                Node2 next = node.nexts.get(path);
                if (next == null) {
                    return 0;
                }
                //  跳转到下一节点
                node = next;
            }
            return node.end;
        }
    }

    public static void main(String[] args) {

        Trie2 trie2 = new Trie2();
        trie2.insert("g");
        trie2.insert("gc");
        trie2.insert("gcp");
        trie2.insert("gcpu");
        trie2.insert("gcpuu");
        trie2.insert("gcpuuz");
        trie2.insert("gcpuuz9");
        trie2.insert("gcpuuz94");
        trie2.insert("gcpuuz94k");
        trie2.insert("gcpuuz94kk");
        trie2.insert("gcpuuz94kkp");
        trie2.insert("gcpuuz94kkp5");

        System.out.println(trie2.prefixNumber("g"));
    }
}