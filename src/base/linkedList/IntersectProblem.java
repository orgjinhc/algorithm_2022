package base.linkedList;

public class IntersectProblem {

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
    public static boolean isIntersectionWithOutNode(Node node1, Node node2) {

        if (node1 == null || node2 == null) {
            return false;
        }

        if (node1 == node2) {
            return true;
        }

        while (node2.next != null) {
            node2 = node2.next;
        }

        while (node1.next != null) {
            node1 = node1.next;
        }
        return node1 == node2;
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
        Node root1 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(9);
        Node node3 = new Node(2);
        root1.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        Node root2 = new Node(0);
        Node node4 = new Node(5);
        Node node5 = new Node(4);
        Node node6 = new Node(2);
        root2.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);

        System.out.println(isIntersection(root1, root2));
    }
}