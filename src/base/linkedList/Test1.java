package base.linkedList;

import java.util.HashSet;

public class Test1 {


    public static class Node {
        Node next;
        int val;

        public Node() {

        }

        public Node(int val) {
            this.val = val;
        }

        public Node(Node next, int val) {
            this.next = next;
            this.val = val;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


    public static void main(String[] args) {

        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        //  0>1>2>3>1
        root.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node1);

        HashSet<Node> set = new HashSet<>();

        Node cur = root;
        while (cur.next != null) {
            if (!set.add(cur)) {
                System.out.println(cur.val);
                break;
            }
            cur = cur.next;
        }
    }
}
