package src;
import game.*;

public class BlessCurseOwner extends ItemAction {
    public BlessCurseOwner(Item _owner) {
        super(_owner);
        System.out.println("Creating BlessCurseOwner: " + _owner);
    }

    @Override
    public void initialize(Player player, ObjectDisplayGrid object, PlayerMovement movement) {}
}
