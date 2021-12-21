package src;
import game.*;

public abstract class ItemAction extends Action {
    private Item owner;

    public ItemAction(Item _owner) {
        System.out.println("Creating an ItemAction: " + _owner);
        owner = _owner;
    }

    public Item getOwner() { return owner; }

    abstract public void initialize(Player player, ObjectDisplayGrid object, PlayerMovement movement);
}
