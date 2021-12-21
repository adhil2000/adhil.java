package src;
import game.*;

import java.util.ArrayList;

public class Player extends Creature implements MovementObserver {
    private Sword sword;
    private Armor armor;
    private ArrayList<Item> pack;
    private int moves = 0;

    public Player() {
        System.out.println("Player Created");
        pack = new ArrayList<Item>();
        setType('@');
    }

    @Override
    // HP MOVES
    public void observerUpdate(int _moves) {
        moves += _moves;
        int hpMoves = getHpMoves();
        if (hpMoves <= moves) {
            int currentHp = getHp() + 1;
            setHp(currentHp);
            moves -= getHpMoves();
        }
    }

    // SWORD RELATED FUNCTIONS
    public boolean setWeapon(int index) {
        System.out.println("Weapon SET");
        Item fromPack = getPack(index);
        if (fromPack instanceof Sword) {
            sword = (Sword) fromPack;
            sword.isEquipped(true);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getSwordValue() {
        return (sword != null) ? sword.getIntValue() : 0;
    }

    public boolean isWeaponEquipped() {
        return (sword != null) ? true : false;
    }

    public boolean swapWeapon() {
        if (isWeaponEquipped()) {
            sword.isEquipped(false);
            sword = null;
            return true;
        } else {
            return false;
        }
    }


    // ARMOR RELATED FUNCTIONS
    public boolean setArmor(int _armor) {
        System.out.println("ARMOR SET");
        Item fromPack = getPack(_armor);
        if (fromPack instanceof Armor) {
            armor = (Armor) fromPack; // Type Cast Needed
            armor.isEquipped(true);
            return true;
        } else {
            return false;
        }
    }

    // Why is this gREY?
    public Armor getArmor() {
        return armor;
    }

    @Override
    public int getArmorValue() {
        return (isArmorEquipped()) ? armor.getIntValue() : 0;
    }

    public void setArmorValue(int val) {
        if (isArmorEquipped()) {
            armor.setIntValue(val);
        }
    }

    public boolean isArmorEquipped() {
        return (armor != null) ? true : false;
    }

    public boolean swapArmor() {
        if (isArmorEquipped()) {
            armor.isEquipped(false);
            armor = null;
            return true;
        } else {
            return false;
        }
    }

    // SCROLL
    public boolean readScroll(int index, ObjectDisplayGrid object, PlayerMovement movement) {
        Item fromPack = getPack(index);
        if (fromPack instanceof Scroll) {
            for (ItemAction action : fromPack.getItemActions()) {
                if (action != null) {
                    action.initialize(this, object, movement);
                }
            }
            return true;
        }
        return false;
    }

    // IM PACKING
    public int addPack(Item item) {
        pack.add(item);
        return pack.size();
    }

    // removes Item from Pack
    public Item removePack(int index) {
        Item fromPack = getPack(index);
        if ((fromPack != null) && (fromPack instanceof Sword)) {
            swapWeapon();
            pack.remove(index);
        } else if ((fromPack != null) && (fromPack instanceof Armor)) {
            swapArmor();
            pack.remove(index);
        }
        else if ((fromPack != null)) {
            pack.remove(index);
        }
        return fromPack;
    }

    // get an item from pack
    public Item getPack(int index) {
        int size = pack.size();
        if (size > index && index >= 0) {
            return pack.get(index);
        } else {
            return null;
        }
    }

    // return ArrayList pack;
    public ArrayList<Item> getPack() {
        return pack;
    }

    // emptyPack
    public boolean emptyPack() {
        return (pack.size() == 0) ? true : false;
    }
}
