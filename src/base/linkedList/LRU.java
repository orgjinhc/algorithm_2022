package base.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * get, 存在返回value, 不存在返回null
 * put, 存在替换old value, 不存在新增, 超过 capacity 移除最早添加的元素
 * 所有操作都在 O(1) 拿下
 */
public class LRU {

    public static void main(String[] args) {
        LRU lRUCache = new LRU(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));   // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }

    MyCache<Integer, Integer> cache;

    public LRU(int capacity) {
        cache = new MyCache(capacity);
    }

    public int get(int key) {
        Integer ans = cache.get(key);
        return ans == null ? -1 : ans;
    }

    public void put(int key, int value) {
        cache.set(key, value);
    }

    /**
     * 缓存类
     *
     * @param <K>
     * @param <V>
     */
    static class MyCache<K, V> {
        Map<K, Node<K, V>> map;
        DoubleNodeLinkedList list;
        int capacity = 0;

        public MyCache(int capacity) {
            this.map = new HashMap<>();
            this.list = new DoubleNodeLinkedList();
            this.capacity = capacity;
        }

        public V get(K k) {
            if (map.containsKey(k)) {
                Node<K, V> ans = map.get(k);
                list.moveNodeToTail(ans);
                return ans.v;
            }
            return null;
        }

        public void set(K k, V v) {
            if (map.containsKey(k)) {
                Node<K, V> existNode = map.get(k);
                existNode.v = v;
                list.moveNodeToTail(existNode);
            } else {
                Node<K, V> newNode = new Node<>(k, v);
                map.put(k, newNode);
                list.addNode(newNode);
                if (map.size() == capacity + 1) {
                    delNodeFromHead();
                }
            }
        }

        /**
         * 双删
         */
        public void delNodeFromHead() {
            Node<K, V> head = list.removeHead();
            map.remove(head.k);
        }
    }


    static class DoubleNodeLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        /**
         * 记录头, 移动head的两个指针
         * 特殊case, 当前链表上有且只有一个节点情况
         *
         * @return
         */
        public Node<K, V> removeHead() {
            Node<K, V> ans = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                ans.next = null;
            }
            return ans;
        }

        public void addNode(Node<K, V> node) {
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.last = tail;
            }
            tail = node;
        }

        /**
         * 将node的前后节点链接, node自动添加到尾部
         * 特殊case, node是head or tail(已经满足, 无需考虑)
         *
         * @param node
         */
        public void moveNodeToTail(Node<K, V> node) {
            if (node == tail) {
                return;
            }
            if (node == head) {
                head = head.next;
                head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            tail.next = node;
            node.last = tail;
            tail = node;
        }
    }

    static class Node<K, V> {
        K k;
        V v;
        Node<K, V> last;
        Node<K, V> next;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }
}