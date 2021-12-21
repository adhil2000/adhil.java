public class Node {

    private int data;
    private Node left = null;
    private Node right = null;

    public Node(int data){
        this.data = data;
        this.right = null;
        this.left = null;
    }
    public Node(Node node){
        this.data = node.data;
        this.left = node.left;
        this.right = node.right;
    }

    public static void addNode(Node node, int d){
        insert(node,d);
    }

    public static Node insert(Node root, int value)
    {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.data) {
            root.left = insert(root.left, value);
        }
        else if (value > root.data) {
            root.right = insert(root.right, value);
        }
        return root;
    }


    public static void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.printf("%s ", node.data);
        printInOrder(node.right);
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getData() {
        return data;
    }

    public static Node copy(Node fromRoot) {
        if (fromRoot == null) {
            return null;
        }
        Node toRoot = new Node(fromRoot.data);
        toRoot.left = copy(fromRoot.left);
        toRoot.right = copy(fromRoot.right);
        return toRoot;
    }
}
