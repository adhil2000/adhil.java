package src;

public class Remove extends CreatureAction {

    private String name;

    public Remove(String _name, Creature _owner) {
        super(_owner);
        System.out.println("Removing: " + _name + _owner);
        name = _name;
    }

    public String getName() { return name; }

    @Override
    public void initialize(ObjectDisplayGrid object) {
        Creature initOwner = getOwner();
        int ownerX = initOwner.getDisplayX();
        int ownerY = initOwner.getDisplayY();

        object.removeDisplayItem(ownerX, ownerY);
    }
}
