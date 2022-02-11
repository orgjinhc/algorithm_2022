package base.linkedList;

public class LoopIntersectProblem {

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 判断是否有环
     * 空节点、一个节点、两个节点情况下都无法构成环结构
     * 构成环的前提是满足三个节点(单链表)
     *
     * @param head
     * @return
     */
    public static Node isLoopBySF(Node head) {
        if (null == head || head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (null == fast.next || null == fast) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;
    }


    /**
     * 判断是否相交
     * 如果相交 最后一个节点肯定相同
     * 如果不相交 最后一个节点肯定不同
     * <p>
     * 找到最后一个节点判断是否相同
     *
     * @param node1
     * @param node2
     * @return
     */
    public static Node isIntersection(Node node1, Node node2) {

        if (node1 == null || node2 == null) {
            return null;
        }

        if (node1 == node2) {
            return null;
        }

        //  计算层级
        int nodeHeight = 0;

        Node root = node1;

        while (root != null) {
            nodeHeight++;
            root = root.next;
        }

        root = node2;
        while (root != null) {
            nodeHeight--;
            root = root.next;
        }

        Node maxCur = nodeHeight > 0 ? node1 : node2;
        Node minCur = maxCur == node1 ? node2 : node1;

        int abs = Math.abs(nodeHeight);
        for (int i = 0; i < abs; i++) {
            maxCur = maxCur.next;
        }

        while (maxCur != minCur) {
            if (maxCur == null || minCur == null) {
                return null;
            }
            maxCur = maxCur.next;
            minCur = minCur.next;
        }
        return maxCur;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        Node loop1 = isLoopBySF(node1);
        Node loop2 = isLoopBySF(node1);

        if (null == loop1 && null == loop2) {
            isIntersection(node1, node1);
        }

        if (loop1 != null && loop2 != null) {
            isLoopIntersection(node1, loop1, node2, loop2);
        }
    }

    /**
     * 环形求相交点
     *
     * @param node1
     * @param loop1
     * @param node2
     * @param loop2
     * @return
     */
    private static Node isLoopIntersection(Node node1, Node loop1, Node node2, Node loop2) {
        if (loop1 == loop2) {
            //  计算层级
            int nodeHeight = 0;

            Node root = node1;

            while (root != loop1) {
                nodeHeight++;
                root = root.next;
            }

            root = node2;
            while (root != loop1) {
                nodeHeight--;
                root = root.next;
            }

            Node maxCur = nodeHeight > 0 ? node1 : node2;
            Node minCur = maxCur == node1 ? node2 : node1;

            int abs = Math.abs(nodeHeight);
            for (int i = 0; i < abs; i++) {
                maxCur = maxCur.next;
            }

            while (maxCur != minCur) {
                if (maxCur == null || minCur == null) {
                    return null;
                }
                maxCur = maxCur.next;
                minCur = minCur.next;
            }
            return maxCur;
        } else {
            //  loop1 != loop2, 分为两种情况
            Node searchNode = loop1.next;
            while (searchNode != loop1) {
                //  第二种:两个环形链表有交点，一个环点遍历过程中碰到另一个环的环点
                if (searchNode == loop2) {
                    return null;
                }
                searchNode = searchNode.next;
            }
            //  第一种:两个环形链表无交点, 任何一个环遍历一周再次来到环点，并没有碰到下一个环点就退出
            return null;
        }
    }
}