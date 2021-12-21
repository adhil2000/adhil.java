public class PekinDuck extends Duck{

    public PekinDuck( ) {
        quackBehavior = new Quack( );
        flyBehavior = new FlysPoorly();
        laysEggsBehavior = new LaysEggsNotBroody();
    }

    public void display( ) {
        System.out.println("I’m a pekin duck");
    }
}
