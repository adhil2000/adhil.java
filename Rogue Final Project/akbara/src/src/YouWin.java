package src;

public class YouWin extends CreatureAction {

    private String name;

    public YouWin(String _name, Creature _owner) {
        super(_owner);
        System.out.println("You have Won: " + _name + _owner);
        name = _name;
    }

    public String getName() { return name; }

    @Override
    public void initialize(ObjectDisplayGrid object) {
        object.displayInfo("You have defeated the Troll!");
    }
}
