package src;
import game.*;

public class BlessArmor extends ItemAction {
    public BlessArmor(Item _owner) {
        super(_owner);
        System.out.println("Creating BlessArmor: " + _owner);
    }

    @Override
    public void initialize(Player player, ObjectDisplayGrid object, PlayerMovement movement) {
        String message = "Scroll of cursing";
        int newArmorValue = player.getArmorValue() + getIntValue();
        player.setArmorValue(newArmorValue);
        if (player.isArmorEquipped()) {
            message += " has cursed Player Armor!";
        } else {
            message += " has done nothing because armor is not being used";
        }
        object.displayInfo(message);
    }
}
