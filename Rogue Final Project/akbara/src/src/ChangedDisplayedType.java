package src;

public class ChangedDisplayedType extends CreatureAction {

    private String name; // stores name

    // ChangedDisplayed Type Constructor
    public ChangedDisplayedType(String _name, Creature _owner) {
        super(_owner);
        System.out.println("DisplayType Changed: " + _name + _owner);
        name = _name;
    }

    // returns name
    public String getName() { return name;}

    // New Addition
    @Override
    public void initialize(ObjectDisplayGrid object) {
        // Get Owner & CharValue
        char initChar = getCharValue();
        Creature initOwner = getOwner();
        // Write Object
        initOwner.setType(initChar);
        int ownerX = initOwner.getDisplayX();
        int ownerY = initOwner.getDisplayY();
        object.writeToTerminal(ownerX, ownerY);
    }
}
