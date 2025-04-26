public class BSTTest {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(5, "five");
        tree.put(2, "two");
        tree.put(8, "eight");
        tree.put(6, "six");
        tree.put(9, "nine");

        for (BST<Integer, String>.Node node : tree) {
            System.out.println("key=" + node.getKey() + ", value=" + node.getValue());
        }

        System.out.println(tree.get(8));
        tree.delete(8);
        System.out.println(tree.size());
        for (BST<Integer, String>.Node node : tree) {
            System.out.println("key=" + node.getKey() + ", value=" + node.getValue());
        }

        tree.delete(2);
        System.out.println(tree.size());
        for (BST<Integer, String>.Node node : tree) {
            System.out.println("key=" + node.getKey() + ", value=" + node.getValue());
        }
    }
}
