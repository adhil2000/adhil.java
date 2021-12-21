public class RedHeadDuck extends Duck {
   public RedHeadDuck( ) {
      quackBehavior = new Quack( );
      flyBehavior = new FlyWithWings( );
      laysEggsBehavior = new LaysEggsBroody();
   }

   public void display( ) {
      System.out.println("I'm a real redheaded duck");
   }
}
