package leetcode.daily;

import leetcode.util.Node;

import java.util.LinkedList;


/**
 * 给定一个二叉树
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有next 指针都被设置为 NULL。
 *
 * 
 *
 * 进阶：
 *
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 */
public class Connect_117 {

    /**
     * 利用两个队列实现
     * 当前层队列和下一层队列, 当前层只在初始化的时机加入root节点, 后续的行为都是赋值下一层队列
     * 下一层队列, 当前每次添加的下一层元素都是添加到下层队列中
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        LinkedList<Node> curLevelNode = new LinkedList<>();
        curLevelNode.add(root);
        while (!curLevelNode.isEmpty()) {
            LinkedList<Node> preLevelNode = new LinkedList<>();
            int size = curLevelNode.size();
            for (int i = 0; i < size; i++) {
                Node curNode = curLevelNode.pollFirst();
                Node peek = curLevelNode.peek();
                if (null != peek) {
                    curNode.next = peek;
                }

                if (curNode.left != null) {
                    preLevelNode.addLast(curNode.left);
                }
                if (curNode.right != null) {
                    preLevelNode.addLast(curNode.right);
                }
            }
            curLevelNode = preLevelNode;
        }
        return root;
    }
}
