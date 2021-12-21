package src;

public class UpdateDisplay extends CreatureAction {

    private String name;

    public UpdateDisplay(String _name, Creature _owner) {
        super(_owner);
        System.out.println("Display Updated: " + _name + _owner);
        name = _name;
    }

    public String getName() { return name; }

    @Override
    public void initialize(ObjectDisplayGrid object) {
        Creature initOwner = getOwner();
        int ownerX = initOwner.getDisplayX();
        int ownerY = initOwner.getDisplayY();

        object.writeToTerminal(ownerX, ownerY);

        if (initOwner instanceof Player) {
            int initHP = initOwner.getHp();

            object.displayHp(initHP);
        }
    }
}
