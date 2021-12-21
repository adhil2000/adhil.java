public class ToyDuck extends Duck{
    public ToyDuck( ) {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Quack( );
        laysEggsBehavior = new LaysToysEggs();
    }

    public void display( ) {
        System.out.println("I’m a toy duck.");
    }
}
