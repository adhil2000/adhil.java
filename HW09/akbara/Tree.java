public class Tree implements Cloneable{

   private Node root = null;

   public Tree(int data) {
      root = new Node(data);
   }

   public Tree(Tree oldTree) {
      // create a root node and then copy oldTree to the tree whose root is the
      // new root node.
      this.root = Node.copy(oldTree.root);
   }

   public Tree clone() throws CloneNotSupportedException {
      Tree tree = (Tree)super.clone();
      return tree;
   }

   public void addNode(int data) {
      root.addNode(root, data);
   }

   public Node getRoot( ) {
      return root;
   }

   public void printInOrder( ) {
      Node.printInOrder(root);
   }
}
