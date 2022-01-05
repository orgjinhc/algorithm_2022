package leetcode.daily.medium;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 实现RandomizedSet 类：
 * <p>
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
 */
public class Daily_380_RandomizedSet {


    /**
     * 存储 val - size
     */
    private Map<Integer, Integer> container = new HashMap<>();

    /**
     * 存在 size - val
     */
    private Map<Integer, Integer> indexMap = new HashMap<>();
    private int size;

    public Daily_380_RandomizedSet() {
        size = 0;
    }

    public boolean insert(int val) {
        if (container.containsKey(val)) {
            return false;
        }

        container.put(val, size);
        indexMap.put(size++, val);
        return true;
    }

    public boolean remove(int val) {
        if (!container.containsKey(val)) {
            return false;
        }

        //  要删除的key对应的index
        int deleteIndex = container.get(val);
        //  当前集合最后一个index
        int lastIndex = --size;
        //  上一个index对应的key
        int lastKey = indexMap.get(lastIndex);

        container.put(lastKey, deleteIndex);
        indexMap.put(deleteIndex, lastKey);

        container.remove(val);
        indexMap.remove(lastIndex);
        return true;
    }

    public int getRandom() {
        if (size == 0) {
            return -1;
        }
        int index = (int) (Math.random() * size);
        return indexMap.get(index);
    }

    public static void main(String[] args) {

        /**
         * RandomizedSet randomizedSet = new RandomizedSet();
         * randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
         * randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
         * randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
         * randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
         * randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
         * randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
         * randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
         */
        Daily_380_RandomizedSet randomizedSet = new Daily_380_RandomizedSet();
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(2));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.getRandom());
    }

}
