package src;

import java.util.*;

public class Creature extends Displayable {

    private String name;
    private String id;
    private String serial;
    private ArrayList<CreatureAction> deathActions = new ArrayList();
    private ArrayList<CreatureAction> hitActions = new ArrayList();

    private Random random = new Random();
    private boolean isAlive = true;

    public Creature() {
        System.out.println("Creating creature");
    }

    public void setName(String name) {
        System.out.println("Setting name: " + name);
        this.name = name;
    }

    public void setID(int room, int serial) {
        System.out.println("set ID to: " + room + serial);
        id = room + "-" + serial;
    }

    public void setDeathAction(CreatureAction da) {
        System.out.println("Setting DeathAction: " + da);
        deathActions.add(da);
    }

    public void setHitAction(CreatureAction ha) {
        System.out.println("Setting HitAction: " + ha);
        hitActions.add(ha);
    }

    public String getName() { return name; }

    public String getID() { return id; }

    public String getSerial() { return serial; }

    public ArrayList<CreatureAction> getDeathActions() {
        return deathActions;
    }

    public ArrayList<CreatureAction> getHitActions() {
        return hitActions;
    }

    public int getSwordValue() {
        return 0;
    }

    public int getArmorValue() {
        return 0;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean val) {
        isAlive = val;
    }

    public int attack(Creature victim, ObjectDisplayGrid object) {
        if (victim.isAlive()) {
            int addOns = getSwordValue() - victim.getArmorValue();
            int damage = random.nextInt(getMaxHit() + 1) + addOns;
            damage = (damage < 0) ? 0 : damage;
            victim.setHp(victim.getHp() - damage);
            for (CreatureAction actions : victim.hitActions) {
                if(actions != null) {
                    actions.initialize(object);
                }
            }

            // Teleport
            if (((victim.getDisplayX() == 2) && (victim.getDisplayY() == 4)) && (victim.getMaxHit() == 5) && (victim.getHp() <= 50))
            {
                Displayable space = object.teleportTo();
                int victimX = victim.getDisplayX();
                int victimY = victim.getDisplayY();
                int spaceX = space.getDisplayX();
                int spaceY = space.getDisplayY();
                Displayable removedObject = object.removeDisplayObj(victimX, victimY);
                object.displayInfo("A creature has teleported somewhere else in the dungeon!");
                object.addDisplayObject(removedObject, spaceX, spaceY);

            }

            //dropPack
            if (((victim.getDisplayX() == 2) && (victim.getDisplayY() == 4)) && (victim.getMaxHit() == 50))
            {
                Player player = (Player) object.getDisplayObject(this.getDisplayX(), this.getDisplayY());
                if (!player.emptyPack()) {
                    Item removedObject = player.getPack(0);
                    object.addDisplayItem(removedObject, this.getDisplayX(), this.getDisplayY());
                    player.removePack(0);
                }
                //object.displayInfo("You have dropped an item from your pack!");
            }

            return damage;
        }
        return -1;
    }
}
