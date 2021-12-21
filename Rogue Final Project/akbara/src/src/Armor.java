package src;

public class Armor extends Item {

    private String id;
    private boolean worn = false;

    // Armor Constructor
    public Armor(String name) {
        System.out.println("Creating Armor: " + name);
        this.setName(name);
        setType(']');
    }

    // Sets Room and ID
    public void setID(int room, int serial) {
        System.out.println("set ID to: " + room + serial);
        id = room + "-" + serial;
    }

    // Determines if Armor is Equipped or not
    public void isEquipped(boolean tf) {
        worn = tf;}

    // returns the set Char
    @Override
    public char getChar() {
        return ']';
    }

    // returns ID
    public String getID() { return id;}

    // returns display name based on weather armor is worn
    @Override
    public String getName() {
        String name = "";
        if (worn) {
            name += "Wearing Armor (" + (getIntValue()) + ")";
        }
        else {
            name = "Armor (" + (getIntValue()) + ")";
        }
        return name;
    }

}