package src;

public class Sword extends Item {

    private String id;
    private boolean wielded = false;

    public Sword(String _name) {
        System.out.println("Creating a Sword: " + _name);
        setName(_name);
        setType(')');
    }

    public void setID(int room, int serial) {
        System.out.println("set ID to: " + room + serial);
        id = room + "-" + serial;
    }

    public void isEquipped(boolean tf) {
        wielded = tf;
    }

    public String getID() { return id; }

    @Override
    public String getName() {
        String name = "";
        if (wielded) {
            name += "Wielding Sword (" + (getIntValue()) + ")";
        }
        else {
            name = "Sword (" + (getIntValue()) + ")";
        }
        return name;
    }

}
