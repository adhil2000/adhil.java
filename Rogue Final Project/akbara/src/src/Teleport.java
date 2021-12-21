package src;

public class Teleport extends CreatureAction {

    private String name;

    public Teleport(String _name, Creature _owner) {
        super(_owner);
        System.out.println("Creating Teleport: " + _name + _owner);
        name = _name;
    }

    public String getName() { return name; }

    @Override
    public void initialize(ObjectDisplayGrid object) {
        // Need to Work on This
    }
}
