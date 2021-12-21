public abstract class Duck {

   public abstract void display( );

   public Duck( ) { }
   
   public void swim( ) {
      System.out.println("All ducks float");
   }
   
   public void performQuack ( ) {
      quackBehavior.quack( );
   }
   
   public void performFly( ) {
      flyBehavior.fly( );
   }

   public void performEggLayingBehavior() { laysEggsBehavior.laysEgg(); }
   
   public void setFlyBehavior(FlyBehavior fb) {
      flyBehavior = fb;
   }
      
   public void setQuackBehavior(QuackBehavior qb) {
      quackBehavior = qb;
   }

   public void setLaysEggsBehavior(LaysEggsBehavior lb) {laysEggsBehavior = lb;}

   protected QuackBehavior quackBehavior;
   protected FlyBehavior flyBehavior;
   protected LaysEggsBehavior laysEggsBehavior;
}
