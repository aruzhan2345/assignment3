import java.util.Iterator;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Node> {
    private Node root;
    private int size = 0;

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return val;
        }
    }

    public int size() {
        return size;
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }
        Node curr = root, parent = null;
        int cmp = 0;
        while (curr != null) {
            parent = curr;
            cmp = key.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else {
                curr.val = val;
                return;
            }
        }
        if (cmp < 0) parent.left = new Node(key, val);
        else parent.right = new Node(key, val);
        size++;
    }

    public V get(K key) {
        Node curr = root;
        while (curr != null) {
            int cmp = key.compareTo(curr.key);
            if (cmp < 0) curr = curr.left;
            else if (cmp > 0) curr = curr.right;
            else return curr.val;
        }
        return null;
    }

    public void delete(K key) {
        Node curr = root, parent = null;
        boolean isLeft = false;
        while (curr != null && !curr.key.equals(key)) {
            parent = curr;
            if (key.compareTo(curr.key) < 0) {
                curr = curr.left;
                isLeft = true;
            } else {
                curr = curr.right;
                isLeft = false;
            }
        }
        if (curr == null) return;


        if (curr.left == null && curr.right == null) {
            if (curr == root) root = null;
            else if (isLeft) parent.left = null;
            else parent.right = null;
        }

        else if (curr.left == null || curr.right == null) {
            Node child = (curr.left != null ? curr.left : curr.right);
            if (curr == root) root = child;
            else if (isLeft) parent.left = child;
            else parent.right = child;
        }

        else {
            Node succParent = curr;
            Node succ = curr.right;
            while (succ.left != null) {
                succParent = succ;
                succ = succ.left;
            }
            curr.key = succ.key;
            curr.val = succ.val;
            if (succParent.left == succ) succParent.left = succ.right;
            else succParent.right = succ.right;
        }
        size--;
    }

    @Override
    public Iterator<Node> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Node> {
        private StackNode top;
        private Node curr = root;

        public BSTIterator() {
            while (curr != null) {
                push(curr);
                curr = curr.left;
            }
        }

        private void push(Node node) {
            top = new StackNode(node, top);
        }

        private Node pop() {
            if (top == null) throw new RuntimeException("No more elements");
            Node node = top.node;
            top = top.next;
            return node;
        }

        private boolean isEmpty() {
            return top == null;
        }

        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public Node next() {
            Node node = pop();
            Node t = node.right;
            while (t != null) {
                push(t);
                t = t.left;
            }
            return node;
        }

        private class StackNode {
            Node node;
            StackNode next;
            StackNode(Node node, StackNode next) {
                this.node = node;
                this.next = next;
            }
        }
    }
}
