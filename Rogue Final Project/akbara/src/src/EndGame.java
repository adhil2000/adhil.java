package src;

public class EndGame extends CreatureAction {

    private String name;

    public EndGame(String _name, Creature _owner) {
        super(_owner);
        System.out.println("EndGame: " + _name + _owner);
        name = _name;
    }

    public String getName() { return name; }

    @Override
    public void initialize(ObjectDisplayGrid object) {
        object.isGame(true);
    }
}
