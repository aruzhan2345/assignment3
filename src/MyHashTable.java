public class MyHashTable<K, V> {
    private static final int DEFAULT_NUMBER_OF_BUCKETS = 50;
    private MyNode<K, V>[] buckets;
    private int size = 0;

    private static class MyNode<K, V> {
        private K key;
        private V value;
        private MyNode<K, V> next;

        public MyNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    public MyHashTable() {
        this(DEFAULT_NUMBER_OF_BUCKETS);
    }

    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        buckets = (MyNode<K, V>[]) new MyNode[capacity];
    }

    private int hash(K key) {
        int h = key.hashCode();
        h = h & 0x7fffffff;
        return h % buckets.length;
    }

    public void put(K key, V value) {
        if (value == null) throw new NullPointerException();
        int index = hash(key);
        MyNode<K, V> head = buckets[index];

        MyNode<K, V> curr = head;
        while (curr != null) {
            if ((curr.key == null && key == null) ||
                    (curr.key != null && curr.key.equals(key))) {
                curr.value = value;
                return;
            }
            curr = curr.next;
        }

        MyNode<K, V> node = new MyNode<>(key, value);
        node.next = head;
        buckets[index] = node;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        MyNode<K, V> curr = buckets[index];
        while (curr != null) {
            if ((curr.key == null && key == null) ||
                    (curr.key != null && curr.key.equals(key))) {
                return curr.value;
            }
            curr = curr.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        MyNode<K, V> curr = buckets[index];
        MyNode<K, V> prev = null;
        while (curr != null) {
            if ((curr.key == null && key == null) ||
                    (curr.key != null && curr.key.equals(key))) {
                if (prev == null) {
                    buckets[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return curr.value;
            }
            prev = curr;
            curr = curr.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (MyNode<K, V> head : buckets) {
            MyNode<K, V> curr = head;
            while (curr != null) {
                if ((curr.value == null && value == null) ||
                        (curr.value != null && curr.value.equals(value))) {
                    return true;
                }
                curr = curr.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (MyNode<K, V> head : buckets) {
            MyNode<K, V> curr = head;
            while (curr != null) {
                if ((curr.value == null && value == null) ||
                        (curr.value != null && curr.value.equals(value))) {
                    return curr.key;
                }
                curr = curr.next;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public int bucketSize(int i) {
        int count = 0;
        MyNode<K, V> curr = buckets[i];
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }
}
