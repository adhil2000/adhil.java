public class Test {

   public static void main(String[ ] args) {

      int hash;

      Remote rc = Remote.buildRemote( );
      hash = rc.hashCode( );

      rc = Remote.buildRemote( );
      if (hash != rc.hashCode( )) {
         System.out.println("Remote should be a singleton, but isn't\n");
      } else {
         System.out.println("Remote seems to properly implement singleton\n");
      }

      // rc = new Remote(1); // THIS SHOULD GIVE AN ERROR, COMMENT IT OUT.

      System.out.println("The new remote:\n"+rc);

      rc.addCommand(0, new Turnon( ));
      rc.addCommand(1, new Turnoff( ));
      rc.addCommand(2, new Turnup( ));
      rc.addCommand(3, new Turndown( ));

      System.out.println("The initialized remote:\n"+rc);

      rc.executeCommand(0);

      for (int i=0; i<5; i++) { 
         rc.executeCommand(2);
      }

      for (int i=0; i<5; i++) { 
         rc.executeCommand(3);
      }

      rc.executeCommand(1);

      rc.executeCommand(4);
      rc.executeCommand(5);

      System.out.println("\n");
      for (int i = 0; i < 6; i++) {
         rc.removeCommand(i);
      }

      System.out.println("\nThe remote after removing commands:\n"+rc);
   }
}
