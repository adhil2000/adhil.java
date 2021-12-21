package src;

public class DropPack extends CreatureAction {

    private String name;

    public DropPack(String _name, Creature _owner) {
        super(_owner);
        System.out.println("DropPack: " + _name + _owner);
        name = _name;
    }

    public String getName() { return name; }

    @Override
    public void initialize(ObjectDisplayGrid object) {
        Player player = (Player) getOwner();
        Item item = player.removePack(0);

        int playerX = player.getDisplayX();
        int playerY = player.getDisplayY();

        if (item == null) {
            object.displayInfo("NO ITEM AT INDEX");
        } else {
            object.addDisplayItem(item, playerX, playerY);
        }

    }
}
