public class Main {
   public static void main(String[ ] args) {
      // uncomment the following line to test part 2
      // comment out to test part 1
      int maxDucks = 5;

      // uncomment out the following line to test part 1
      // comment out to test part 2
      // int maxDucks = 4;
      Duck ducks[ ] = new Duck[maxDucks];
      ducks[0] = new MallardDuck( );
      ducks[1] = new RedHeadDuck( );
      ducks[2] = new DecoyDuck( );
      ducks[3] = new PekinDuck( );

      // uncomment out the following lines to test part 2
      // comment out to test part 1
      ducks[4] = new ToyDuck( );
      for (int i=0; i<maxDucks; i++) {
         ducks[i].display( );
         ducks[i].performQuack( );
         ducks[i].performFly( );
         ducks[i].swim( );
         ducks[i].performEggLayingBehavior( );
         System.out.println("");
      }
   }
}
