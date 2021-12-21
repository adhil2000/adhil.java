import java.util.Random;

public class HW9 {

   private static boolean checkStructure(Node t1, Node t2) {
      if (((t1 == null) && (t2 != null)) ||  ((t1 != null) && (t2 == null))) {
         System.out.println("structure mismatch: ");
         return false;
      }
 
      boolean retLeft = true;
      boolean retRight = true;
      if ((t1 != null) && (t2 != null)) { 
         if (t1.getData( ) != t2.getData( )) {
            System.out.println("data mismatch: "+t1.getData( )+", "+t2.getData( ));
            return false;
         }

         if (t1.hashCode( ) == t2.hashCode( )) {
            System.out.print("duplicate node: "+t1.getData( )+", "+t2.getData( ));
            System.out.println(", "+t1.hashCode( )+", "+t2.hashCode( ));
            return false;
         }

         retLeft = checkStructure(t1.getLeft( ), t2.getLeft( ));
         retRight = checkStructure(t1.getRight( ), t2.getRight( ));
      }
      return retLeft && retRight;
   }

   public static void main(String[ ] args) throws CloneNotSupportedException{  // ASK ABOUT THIS

      Random rando = new Random(0);
      Tree tree = new Tree(rando.nextInt( )%100);
      for (int i=0; i < 20; i++) {
         tree.addNode(rando.nextInt( )%100);
      }

     System.out.println("Print of tree");
     tree.printInOrder( );
     System.out.println("");

     Tree tree2 = new Tree(tree);
     if (checkStructure(tree.getRoot( ), tree2.getRoot( ))) {
        System.out.println("Copy is ok");
     }

     System.out.println("Print of tree2");
     tree2.printInOrder( );
     System.out.println("");

     Tree tree3 = (Tree) tree2.clone( );
     if (checkStructure(tree.getRoot( ), tree2.getRoot( ))) {
        System.out.println("Copy is ok");
     }

     System.out.println("Print of tree3");
     tree3.printInOrder( );
     System.out.println("");
 
   }
}
