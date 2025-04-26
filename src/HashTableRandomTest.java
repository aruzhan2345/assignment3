public class HashTableRandomTest {
    public static void main(String[] args) {
        final int BUCKETS = 50;
        final int N = 10000;
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>(BUCKETS);

        for (int i=0; i < N; i++) {
            int id = (int)(Math.random() * Integer.MAX_VALUE);
            MyTestingClass key = new MyTestingClass(id);
            table.put(key, i);
        }

        System.out.println("Bucket -> count");
        for (int b=0; b < BUCKETS; b++) {
            System.out.println(b +" -> "+ table.bucketSize(b));
        }
    }
}